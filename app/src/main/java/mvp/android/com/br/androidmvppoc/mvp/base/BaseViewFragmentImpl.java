package mvp.android.com.br.androidmvppoc.mvp.base;

import android.content.Intent;
import android.os.Bundle;

import mvp.android.com.br.androidmvppoc.domain.Item;

public interface BaseViewFragmentImpl extends BaseViewImpl {
    void showProgressBar(int visibilidade);

    void runOnUiThread(Runnable action);

    void gotoNextScreen(Class<?> cls);

    void gotoNextScreen(Class<?> cls, int requestCode);

    void gotoNextScreen(Class<?> cls, String dataBundle, String keyBundle);

    void gotoNextScreen(Class<?> cls, Bundle bundle);

    void gotoNextScreen(Class<?> cls, Bundle bundle, int requestCode);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onItemAdapterClick(Item item);
}
