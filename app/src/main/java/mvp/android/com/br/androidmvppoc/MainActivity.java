package mvp.android.com.br.androidmvppoc;

import android.content.Intent;
import android.os.Bundle;

import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewFragmentImpl;
import mvp.android.com.br.androidmvppoc.mvp.presenter.MainPresenter;
import mvp.android.com.br.androidmvppoc.mvp.view.MainViewImpl;
import mvp.android.com.br.androidmvppoc.views.activities.BottomBarActivity;


public class MainActivity extends BottomBarActivity<MainPresenter> implements MainViewImpl {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottom_bar;
    }

    @Override
    public void myOnCreate(Bundle savedInstanceState) {
        super.myOnCreate(savedInstanceState);
    }

    @Override
    protected BasePresenterImpl newPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_POSITION_ITEM_MENU, getCurrentIdItemSelected());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void myOnStart() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            BaseViewFragmentImpl fragment = getCurrentFragment();
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void showProgressBar(int visibilidade) {

    }

    @Override
    public void onItemAdapterClick(Item item) {

    }

    @Override
    public BaseViewFragmentImpl getFragment() {
        return getCurrentFragment();
    }
}
