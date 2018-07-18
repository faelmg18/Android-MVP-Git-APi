package mvp.android.com.br.androidmvppoc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;

/**
 * Created by rafael.alves on 16/07/2018.
 */

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public List<T> dataList;
    public BasePresenterImpl mBasePresenterImpl;

    protected abstract VH myOnCreateViewHolder(View parent, int viewType);

    protected abstract void myOnBindViewHolder(VH holder, int position, T item);

    protected abstract int getLayoutId();

    protected void onClean() {
    }

    public BaseRecyclerViewAdapter(List<T> dataList, BasePresenterImpl mBasePresenterImpl) {
        this.dataList = dataList;
        this.mBasePresenterImpl = mBasePresenterImpl;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(getLayoutId(), parent, false);

        return myOnCreateViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        myOnBindViewHolder(holder, position, dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItem(T value) {
        dataList.add(value);
        notifyDataSetChanged();
    }

    public void addAll(List<T> values) {
        dataList.addAll(values);
        notifyItemInserted(dataList.size());
        notifyItemRangeChanged(dataList.size() - 1, dataList.size());
        //notifyDataSetChanged();
    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataList.size());
    }

    public void clear() {
        if (dataList != null && dataList.size() > 0) {
            int size = this.dataList.size();
            this.dataList.clear();
            onClean();
            notifyItemRangeRemoved(0, size);
        }
    }

    public List<T> getDataList() {
        return dataList;
    }
}
