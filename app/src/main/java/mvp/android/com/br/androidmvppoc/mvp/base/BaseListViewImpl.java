package mvp.android.com.br.androidmvppoc.mvp.base;

import mvp.android.com.br.androidmvppoc.domain.Item;

public interface BaseListViewImpl extends BaseViewImpl {
    void onItemAdapterClick(Item item);
}
