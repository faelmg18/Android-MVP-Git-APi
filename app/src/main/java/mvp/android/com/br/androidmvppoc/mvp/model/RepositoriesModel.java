package mvp.android.com.br.androidmvppoc.mvp.model;

import mvp.android.com.br.androidmvppoc.mvp.presenter.RepositoriesPresenter;
import mvp.android.com.br.androidmvppoc.network.RepositoryService;

public class RepositoriesModel implements RepositoriesModelImpl {

    private RepositoriesPresenter presenter;

    public RepositoriesModel(RepositoriesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void retrieveGitRepositories(int page) {
        RepositoryService.getInstance(this.presenter).getRepositories(page);
    }
}
