package mvp.android.com.br.androidmvppoc.mvp.presenter;

import android.os.Bundle;

import mvp.android.com.br.androidmvppoc.domain.GitMySelf;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;

public interface ProfilePresenterImpl extends BasePresenterImpl {
    void retrieveProfile(Bundle savedInstanceState);

    void updateView(GitMySelf gitMySelf);

    void runOnUiThread(Runnable action);

    GitMySelf getGitMySelf();
}
