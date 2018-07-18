package mvp.android.com.br.androidmvppoc.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import mvp.android.com.br.androidmvppoc.domain.GitMySelf;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewImpl;
import mvp.android.com.br.androidmvppoc.mvp.model.ProfileModel;
import mvp.android.com.br.androidmvppoc.mvp.model.ProfileModelImpl;
import mvp.android.com.br.androidmvppoc.mvp.view.ProfileViewImpl;
import mvp.android.com.br.androidmvppoc.views.activities.BaseActivity;

public class ProfilePresenter implements ProfilePresenterImpl {

    public static final String PROFILE_KEY = "my_profile_git";

    ProfileViewImpl view;
    ProfileModelImpl model;
    GitMySelf gitMySelf;

    public ProfilePresenter() {
        model = new ProfileModel(this);
    }

    @Override
    public void retrieveProfile(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            this.gitMySelf = (GitMySelf) savedInstanceState.getSerializable(PROFILE_KEY);
            showProgressBar(false);
            updateView(gitMySelf);
            return;
        }

        model.getMyProfile();
    }

    @Override
    public void updateView(GitMySelf gitMySelf) {
        this.gitMySelf = gitMySelf;
        this.view.updateView();
    }

    @Override
    public void setView(BaseViewImpl view) {
        this.view = (ProfileViewImpl) view;
    }

    @Override
    public BaseActivity getContext() {
        return (BaseActivity) view;
    }

    @Override
    public void runOnUiThread(Runnable action) {
        this.view.runOnUiThread(action);
    }

    @Override
    public GitMySelf getGitMySelf() {
        return this.gitMySelf;
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar(visibilidade);
    }
}
