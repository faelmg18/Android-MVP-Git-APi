package mvp.android.com.br.androidmvppoc.mvp.presenter;

import android.os.Bundle;

import java.util.ArrayList;

import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;

public interface PullRequestPresenterImpl extends BasePresenterImpl {
    void retrievePullRequests(Bundle savedInstanceState);

    ArrayList<GitPullRequests> getGitPullRequests();

    void updateListRecycler(ArrayList<GitPullRequests> items);

    void updateItemRecycler(GitPullRequests item);

    void onItemAdapterClick(GitPullRequests item);

    void runOnUiThread(Runnable action);
}
