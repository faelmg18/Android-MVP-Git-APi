package mvp.android.com.br.androidmvppoc.adapter;

import android.view.View;

import java.util.List;

import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.adapter.viewholder.GitRepositoriesViewHolder;
import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.presenter.RepositoriesPresenterImpl;


/**
 * Created by rafael.alves on 16/01/2018.
 */

public class GitRepositoriesAdapter extends BaseRecyclerViewAdapter<Item, GitRepositoriesViewHolder> {

    public GitRepositoriesAdapter(List<Item> dataList, RepositoriesPresenterImpl presenter) {
        super(dataList, presenter);
    }

    @Override
    protected GitRepositoriesViewHolder myOnCreateViewHolder(View parent, int viewType) {
        return new GitRepositoriesViewHolder(parent, mBasePresenterImpl);
    }

    @Override
    protected void myOnBindViewHolder(GitRepositoriesViewHolder holder, int position, final Item item) {
        holder.bind(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.git_repository_item_adapter;
    }
}
