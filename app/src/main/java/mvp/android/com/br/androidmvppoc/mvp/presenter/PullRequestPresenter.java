package mvp.android.com.br.androidmvppoc.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.mvp.model.PullRequestModel;
import mvp.android.com.br.androidmvppoc.mvp.view.PullRequestViewImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewImpl;
import mvp.android.com.br.androidmvppoc.views.activities.BaseActivity;

public class PullRequestPresenter implements PullRequestPresenterImpl {

    public static final String PULLREQUEST_KEY = "pullrequest_key";

    private final PullRequestModel model;
    private ArrayList<GitPullRequests> items = new ArrayList<>();
    private PullRequestViewImpl view;

    public PullRequestPresenter() {
        model = new PullRequestModel(this);
    }

    @Override
    public void retrievePullRequests(Bundle bundle) {
        if (bundle != null && bundle.getSerializable(PULLREQUEST_KEY) != null) {
            items = (ArrayList<GitPullRequests>) bundle.getSerializable(PULLREQUEST_KEY);
            if (items != null) {
                view.showProgressBar(View.INVISIBLE);
                return;
            }
        }

        String user;
        String repositoryName;

        if (bundle != null) {
            user = bundle.getString("user");
            repositoryName = bundle.getString("repositoryName");
            view.updateTitle(repositoryName);
            model.getPullRequests(user, repositoryName);
        }
    }

    @Override
    public ArrayList<GitPullRequests> getGitPullRequests() {
        return items;
    }

    @Override
    public void updateListRecycler(ArrayList<GitPullRequests> items) {
        this.items.clear();
        this.items.addAll(items);
        view.updateListRecycler();
    }

    @Override
    public void updateItemRecycler(GitPullRequests item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getUser().getLogin().equals(item.getUser().getLogin())) {
                //this.items.get(i).setEhFavorito(m.isEhFavorito());
                view.updateItemRecycler(i);
                break;
            }
        }
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

    @Override
    public void onItemAdapterClick(GitPullRequests item) {
        view.onItemAdapterClick(item);
    }

    @Override
    public void setView(BaseViewImpl view) {
        this.view = (PullRequestViewImpl) view;
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
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar(visibilidade);
    }
}
