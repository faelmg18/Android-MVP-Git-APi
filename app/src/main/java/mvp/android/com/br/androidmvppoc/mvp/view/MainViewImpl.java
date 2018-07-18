package mvp.android.com.br.androidmvppoc.mvp.view;

import mvp.android.com.br.androidmvppoc.mvp.base.BaseListViewImpl;

public interface MainViewImpl extends BaseListViewImpl {
    void runOnUiThread(Runnable action);
}
