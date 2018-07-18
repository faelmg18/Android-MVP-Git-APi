package mvp.android.com.br.androidmvppoc.network;

import java.util.List;

import mvp.android.com.br.androidmvppoc.domain.GitMySelf;
import mvp.android.com.br.androidmvppoc.domain.GitPullRequests;
import mvp.android.com.br.androidmvppoc.domain.GitRepositories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepositoryInterface {
    @GET("search/repositories")
    Call<GitRepositories> doGetListRepositoriesModel(@Query("q") String language,
                                                     @Query("sort") String sort,
                                                     @Query("page") int page);

    @GET("repos/{user}/{repository}/pulls?state=all")
    Call<List<GitPullRequests>> listPullRequests(
            @Path("user") String user,
            @Path("repository") String repository);

    @GET("users/faelmg18")
    Call<GitMySelf> doGetGitMySelf();
}
