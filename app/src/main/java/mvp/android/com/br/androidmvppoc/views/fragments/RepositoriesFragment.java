package mvp.android.com.br.androidmvppoc.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import mvp.android.com.br.androidmvppoc.ApplicationContext;
import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.adapter.GitRepositoriesAdapter;
import mvp.android.com.br.androidmvppoc.components.RecyclerViewEndlessScroll;
import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewFragmentImpl;
import mvp.android.com.br.androidmvppoc.mvp.presenter.RepositoriesPresenter;
import mvp.android.com.br.androidmvppoc.mvp.view.RepositoriesViewImpl;
import mvp.android.com.br.androidmvppoc.views.activities.PullRequestActivity;

public class RepositoriesFragment extends BaseFragments<RepositoriesPresenter> implements RepositoriesViewImpl, RecyclerViewEndlessScroll.EndlessScrollListener {

    private static final int GIT_MAX_PAG_REPOSITORIES = 34;

    private int currentPage;
    private GitRepositoriesAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerViewEndlessScroll recyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    @Override
    protected RepositoriesPresenter newPresenter() {
        return new RepositoriesPresenter();
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    void myOnCreate(View view, Bundle savedInstanceState) {
        getPresenter().retrieveGitRepositories(savedInstanceState, currentPage);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.addEndlessScroll(mLayoutManager, this);

        this.adapter = new GitRepositoriesAdapter(getPresenter().getRepository(), getPresenter());
        recyclerView.setAdapter(this.adapter);
    }

    @Override
    public void onEndlessScroll() {
        if (currentPage == GIT_MAX_PAG_REPOSITORIES) {
            recyclerView.setNoMoreItems(true);
            return;
        }

        currentPage++;
        recyclerView.setLoading(true);
        getPresenter().retrieveGitRepositories(null, currentPage);
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
    public void showToast(String mensagem) {
        getActivityBase().showToast(mensagem);
    }

    @Override
    public void onItemAdapterClick(Item item) {
        gotoPullRequestsActivity(item);
    }

    @Override
    public void showProgressBar(int visibilidade) {
        progressBar.setVisibility(visibilidade);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(RepositoriesPresenter.REPOSITORIES_KEY, getPresenter().getRepository());
        super.onSaveInstanceState(outState);
    }

    @Override
    public BaseViewFragmentImpl getFragment() {
        return this;
    }

    private void gotoPullRequestsActivity(Item item) {
        Intent intent = new Intent(ApplicationContext.getAppContext(), PullRequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("user", item.getOwner().getLogin());
        bundle.putString("repositoryName", item.getName());
        intent.putExtras(bundle);
        ApplicationContext.getAppContext().startActivity(intent);
    }
}