package mvp.android.com.br.androidmvppoc.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.android.com.br.androidmvppoc.R;
import mvp.android.com.br.androidmvppoc.domain.GitMySelf;
import mvp.android.com.br.androidmvppoc.domain.Item;
import mvp.android.com.br.androidmvppoc.mvp.presenter.ProfilePresenter;
import mvp.android.com.br.androidmvppoc.mvp.view.ProfileViewImpl;

import static mvp.android.com.br.androidmvppoc.mvp.presenter.ProfilePresenter.PROFILE_KEY;

public class ProfileFragment extends BaseFragments<ProfilePresenter> implements ProfileViewImpl {

    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    @BindView(R.id.text_view_user_name)
    TextView textViewUserName;
    @BindView(R.id.text_view_user_bio)
    TextView textViewUserBio;
    @BindView(R.id.text_view_repositories)
    TextView textViewRepositories;
    @BindView(R.id.text_view_followers)
    TextView textViewFollowers;
    @BindView(R.id.text_view_following)
    TextView textViewFollowing;
    @BindView(R.id.profile_avatar)
    CircleImageView circleImageView;
    ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected ProfilePresenter newPresenter() {
        return new ProfilePresenter();
    }

    @Override
    int getLayoutId() {
        return R.layout.profile_popup_datail;
    }

    @Override
    void myOnCreate(View view, Bundle savedInstanceState) {
        getPresenter().retrieveProfile(savedInstanceState);
    }

    @Override
    public void updateView() {
        GitMySelf gitMySelf = getPresenter().getGitMySelf();
        textViewUserName.setText(gitMySelf.getName());
        textViewUserBio.setText(gitMySelf.getBio());
        textViewRepositories.setText(String.valueOf(gitMySelf.getPublicRepos()));
        textViewFollowers.setText(String.valueOf(gitMySelf.getFollowers()));
        textViewFollowing.setText(String.valueOf(gitMySelf.getFollowing()));
        imageLoader.displayImage(gitMySelf.getAvatarUrl(), circleImageView);
    }

    @Override
    public void showToast(String mensagem) {
        showToast(mensagem);
    }

    @Override
    public void onItemAdapterClick(Item item) {
    }

    @OnClick(R.id.custom_linear_layout_goto_my_git)
    public void gotoGitMySelf() {
        Uri uri = Uri.parse("https://github.com/faelmg18/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(PROFILE_KEY, getPresenter().getGitMySelf());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showProgressBar(int visibilidade) {
        progressBar.setVisibility(visibilidade);
    }
}
