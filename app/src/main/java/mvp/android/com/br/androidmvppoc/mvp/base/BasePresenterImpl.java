package mvp.android.com.br.androidmvppoc.mvp.base;

import mvp.android.com.br.androidmvppoc.views.activities.BaseActivity;

public interface BasePresenterImpl {
    void setView(BaseViewImpl view);

    BaseActivity getContext();

    void runOnUiThread(Runnable action);

    void showToast(String message);

    void showProgressBar(boolean status);
}
