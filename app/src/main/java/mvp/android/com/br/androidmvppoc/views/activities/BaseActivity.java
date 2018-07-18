package mvp.android.com.br.androidmvppoc.views.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewImpl;

public abstract class BaseActivity<T extends BasePresenterImpl> extends AppCompatActivity implements BaseViewImpl {

    protected abstract int getLayoutId();

    protected abstract void myOnCreate(Bundle savedInstanceState);

    protected abstract T newPresenter();

    protected abstract void myOnStart();

    private T presenter;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (presenter == null) {
            presenter = newPresenter();
        }

        presenter.setView(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        myOnCreate(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    protected void addBackButton(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        myOnStart();
    }

    public T getPresenter() {
        return (T) presenter;
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
