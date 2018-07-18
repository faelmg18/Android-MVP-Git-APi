package mvp.android.com.br.androidmvppoc.views.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import butterknife.BindView;
import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.adapter.GitPullRequestsAdapter;
import mvp.android.com.br.androidmvppoc.components.RecyclerViewEndlessScroll;
import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewFragmentImpl;
import mvp.android.com.br.androidmvppoc.mvp.presenter.PullRequestPresenter;
import mvp.android.com.br.androidmvppoc.mvp.presenter.PullRequestPresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.view.PullRequestViewImpl;

public class PullRequestActivity extends BaseActivity<PullRequestPresenterImpl> implements PullRequestViewImpl, RecyclerViewEndlessScroll.EndlessScrollListener {

    @BindView(R.id.recycler_view)
    RecyclerViewEndlessScroll recyclerView;
    GitPullRequestsAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.pull_requests_activity;
    }

    @Override
    protected void myOnCreate(Bundle savedInstanceState) {
        addBackButton();
        getPresenter().retrievePullRequests(savedInstanceState == null ? getIntent().getExtras() : savedInstanceState);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.addEndlessScroll(mLayoutManager, this);
        adapter = new GitPullRequestsAdapter(getPresenter().getGitPullRequests(), getPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateTitle(String title) {
        setTitle(title);
    }

    @Override
    public void updateListRecycler() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateItemRecycler(int posicao) {
        adapter.notifyItemChanged(posicao);
    }

    @Override
    public void onItemAdapterClick(GitPullRequests item) {

    }

    @Override
    protected PullRequestPresenterImpl newPresenter() {
        return new PullRequestPresenter();
    }

    @Override
    protected void myOnStart() {

    }

    @Override
    public void showProgressBar(int visibilidade) {
        findViewById(R.id.progress_bar).setVisibility(visibilidade);
    }

    @Override
    public void onItemAdapterClick(Item item) {

    }

    @Override
    public BaseViewFragmentImpl getFragment() {
        return null;
    }

    @Override
    public void onEndlessScroll() {

    }
}
