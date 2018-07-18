package mvp.android.com.br.androidmvppoc.network;

import java.util.ArrayList;
import java.util.List;

import mvp.android.com.br.androidmvppoc.communication.APIClientResponseListener;
import mvp.android.com.br.androidmvppoc.communication.HttpRequest;
import mvp.android.com.br.androidmvppoc.domain.GitMySelf;
import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.domain.GitRepositories;
import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.presenter.ProfilePresenter;
import mvp.android.com.br.androidmvppoc.mvp.presenter.ProfilePresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.presenter.PullRequestPresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.presenter.RepositoriesPresenter;
import retrofit2.Call;

public class RepositoryService extends HttpRequest {

    private static final String REPOSITORIES_LANGUAGE = "language:Java";
    private static final String REPOSITORIES_SORT = "stars";

    private static volatile RepositoryService instance;
    private static RepositoryInterface repositoryInterface;


    public RepositoryService(BasePresenterImpl activity) {
        super(activity);
    }

    public static RepositoryService getInstance(BasePresenterImpl presenter) {
        instance = new RepositoryService(presenter);
        repositoryInterface = retrofit.create(RepositoryInterface.class);
        return instance;
    }

    public void getRepositories(int page) {

        Call<GitRepositories> call = repositoryInterface
                .doGetListRepositoriesModel(REPOSITORIES_LANGUAGE, REPOSITORIES_SORT, page);

        execute(call, new APIClientResponseListener<GitRepositories>() {
            @Override
            public void onSuccess(GitRepositories obj) {
                ((RepositoriesPresenter) getPresenter()).updateListaRecycler((ArrayList<Item>) obj.items);
            }

            @Override
            public void onError(Call<GitRepositories> obj, Throwable t) {
                ((RepositoriesPresenter) getPresenter()).showToast(t.getMessage());
            }
        });
    }

    public void getPullRequests(String user, String repository) {
        Call<List<GitPullRequests>> call = repositoryInterface
                .listPullRequests(user, repository);

        execute(call, new APIClientResponseListener<List<GitPullRequests>>() {
            @Override
            public void onSuccess(List<GitPullRequests> obj) {
                ((PullRequestPresenterImpl) getPresenter()).updateListRecycler((ArrayList<GitPullRequests>) obj);
            }

            @Override
            public void onError(Call<List<GitPullRequests>> obj, Throwable t) {
                getPresenter().showToast(t.getMessage());
            }
        });
    }

    public void getMyProfile() {
        Call<GitMySelf> call = repositoryInterface.doGetGitMySelf();
        execute(call, new APIClientResponseListener<GitMySelf>() {
            @Override
            public void onSuccess(GitMySelf obj) {
                ((ProfilePresenterImpl) getPresenter()).updateView(obj);
            }

            @Override
            public void onError(Call<GitMySelf> obj, Throwable t) {
                getPresenter().showToast(t.getMessage());
            }
        });
    }
}
