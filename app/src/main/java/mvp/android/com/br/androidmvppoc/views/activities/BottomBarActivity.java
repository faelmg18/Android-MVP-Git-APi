package mvp.android.com.br.androidmvppoc.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import butterknife.BindView;
import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewFragmentImpl;
import mvp.android.com.br.androidmvppoc.views.fragments.ProfileFragment;
import mvp.android.com.br.androidmvppoc.views.fragments.RepositoriesFragment;

/**
 * Created by rafael.alves on 08/02/2018.
 */

public abstract class BottomBarActivity<T extends BasePresenterImpl> extends BaseActivity {
    protected static final String CURRENT_POSITION_ITEM_MENU = "currentIdItemSelected";

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private BaseViewFragmentImpl currentFragment;
    private int currentIdItemSelected;

    @Override
    protected void myOnCreate(Bundle savedInstanceState) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState != null) {
            return;
        }
        gotoFragment(RepositoriesFragment.class, null);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            currentIdItemSelected = item.getItemId();
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    gotoFragment(ProfileFragment.class, null);
                    return true;
                case R.id.navigation_home:
                    gotoFragment(RepositoriesFragment.class, null);
                    return true;
            }
            return false;
        }
    };


    protected void gotoFragment(Class<? extends Fragment> toFragment, Bundle bundle) {
        Fragment fragment = null;
        try {
            fragment = toFragment.newInstance();
            fragment.setArguments(bundle);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (fragment == null)
            return;

        currentFragment = (BaseViewFragmentImpl) fragment;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    public BaseViewFragmentImpl getCurrentFragment() {
        return currentFragment;
    }

    public int getCurrentIdItemSelected() {
        return currentIdItemSelected;
    }
}
