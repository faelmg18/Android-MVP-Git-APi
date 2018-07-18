package mvp.android.com.br.androidmvppoc.mvp.model;

import mvp.android.com.br.androidmvppoc.mvp.presenter.PullRequestPresenter;
import mvp.android.com.br.androidmvppoc.network.RepositoryService;

public class PullRequestModel implements PullRequestModelImpl {

    private PullRequestPresenter presenter;

    public PullRequestModel(PullRequestPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPullRequests(String user, String repository) {
        RepositoryService.getInstance(presenter).getPullRequests(user, repository);
    }
}
