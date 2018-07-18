package mvp.android.com.br.androidmvppoc.mvp.view;

import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseListViewImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewImpl;

public interface PullRequestViewImpl extends BaseListViewImpl {

    void updateTitle(String title);

    void updateListRecycler();

    void updateItemRecycler(int posicao);

    void onItemAdapterClick(GitPullRequests item);
}
