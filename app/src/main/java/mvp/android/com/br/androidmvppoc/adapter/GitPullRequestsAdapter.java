package mvp.android.com.br.androidmvppoc.adapter;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.adapter.viewholder.GitPullRequestsViewHolder;
import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.mvp.presenter.PullRequestPresenterImpl;


/**
 * Created by rafael.alves on 16/01/2018.
 */

public class GitPullRequestsAdapter extends BaseRecyclerViewAdapter<GitPullRequests, GitPullRequestsViewHolder> {

    public GitPullRequestsAdapter(ArrayList<GitPullRequests> dataList, PullRequestPresenterImpl presenter) {
        super(dataList, presenter);
    }

    @Override
    protected GitPullRequestsViewHolder myOnCreateViewHolder(View parent, int viewType) {
        return new GitPullRequestsViewHolder(parent, (PullRequestPresenterImpl) mBasePresenterImpl);
    }

    @Override
    protected void myOnBindViewHolder(GitPullRequestsViewHolder holder, int position, GitPullRequests item) {
        holder.bind(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.git_pull_requests_item_adapter;
    }
}
