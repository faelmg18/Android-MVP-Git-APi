package mvp.android.com.br.androidmvppoc.mvp.base;

import android.support.v4.app.Fragment;

import mvp.android.com.br.androidmvppoc.domain.Item;

public interface BaseViewImpl {
    void showToast(String mensagem);

    void showProgressBar(int visibilidade);

    void runOnUiThread(Runnable action);

    BaseViewFragmentImpl getFragment();
}
