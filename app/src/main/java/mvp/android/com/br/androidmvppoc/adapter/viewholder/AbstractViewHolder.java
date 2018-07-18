package mvp.android.com.br.androidmvppoc.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;

/**
 * Created by rafael.alves on 16/07/2018.
 */

public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder {

    public abstract void bind(T item);

    private BasePresenterImpl presenter;

    public AbstractViewHolder(View itemView, BasePresenterImpl presenter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.presenter = presenter;
    }

    public BasePresenterImpl getPresenter() {
        return presenter;
    }
}
