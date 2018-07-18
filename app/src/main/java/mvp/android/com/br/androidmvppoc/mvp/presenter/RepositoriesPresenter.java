package mvp.android.com.br.androidmvppoc.mvp.presenter;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.model.RepositoriesModel;
import mvp.android.com.br.androidmvppoc.mvp.model.RepositoriesModelImpl;
import mvp.android.com.br.androidmvppoc.mvp.view.RepositoriesViewImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewImpl;
import mvp.android.com.br.androidmvppoc.views.activities.BaseActivity;

public class RepositoriesPresenter implements RepositoriesPresenterImpl {

    public static final String REPOSITORIES_KEY = "repositories_key";
    public static final String PAGE_KEY = "page_key";

    private RepositoriesModelImpl model;
    private RepositoriesViewImpl view;
    ArrayList<Item> items = new ArrayList<>();
    private int currentPage;

    public RepositoriesPresenter() {
        model = new RepositoriesModel(this);
    }

    @Override
    public void retrieveGitRepositories(Bundle savedInstanceState, int page) {
        if (savedInstanceState != null) {
            items = (ArrayList<Item>) savedInstanceState.getSerializable(REPOSITORIES_KEY);
            page = savedInstanceState.getInt(PAGE_KEY);
            currentPage = page;
            view.showProgressBar(View.GONE);
            return;
        }
        currentPage = page;
        model.retrieveGitRepositories(page);
    }

    @Override
    public ArrayList<Item> getRepository() {
        return items;
    }

    @Override
    public void updateListaRecycler(ArrayList<Item> items) {
        if (currentPage == 0) {
            this.items.clear();
        }
        this.items.addAll(items);
        view.updateListRecycler();
    }

    @Override
    public void updateItemRecycler(Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId() == item.getId()) {
                //this.items.get(i).setEhFavorito(m.isEhFavorito());
                view.updateItemRecycler(i);
                break;
            }
        }
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

    @Override
    public void onItemAdapterClick(Item item) {
        view.onItemAdapterClick(item);
    }

    @Override
    public void runOnUiThread(Runnable action) {
        view.runOnUiThread(action);
    }

    @Override
    public void setView(BaseViewImpl view) {
        this.view = (RepositoriesViewImpl) view;
    }

    @Override
    public BaseActivity getContext() {
        return (BaseActivity) view;
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar(visibilidade);
    }
}
