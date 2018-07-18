package mvp.android.com.br.androidmvppoc.mvp.presenter;

import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewImpl;
import mvp.android.com.br.androidmvppoc.mvp.view.MainViewImpl;
import mvp.android.com.br.androidmvppoc.views.activities.BaseActivity;

public class MainPresenter implements BasePresenterImpl {

    private MainViewImpl view;

    @Override
    public void setView(BaseViewImpl view) {
        this.view = (MainViewImpl) view;
    }

    @Override
    public BaseActivity getContext() {
        return (BaseActivity) view;
    }

    @Override
    public void runOnUiThread(Runnable action) {
        view.runOnUiThread(action);
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

    @Override
    public void showProgressBar(boolean status) {

    }
}
