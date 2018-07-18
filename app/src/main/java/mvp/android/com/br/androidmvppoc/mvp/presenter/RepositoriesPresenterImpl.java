package mvp.android.com.br.androidmvppoc.mvp.presenter;

import android.os.Bundle;

import java.util.ArrayList;

import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;

public interface RepositoriesPresenterImpl extends BasePresenterImpl {
    void retrieveGitRepositories(Bundle savedInstanceState, int page);

    ArrayList<Item> getRepository();

    void updateListaRecycler(ArrayList<Item> items);

    void updateItemRecycler(Item item);

    void showToast(String message);

    void onItemAdapterClick(Item item);

    void runOnUiThread(Runnable action);
}
