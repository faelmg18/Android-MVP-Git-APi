package mvp.android.com.br.androidmvppoc.mvp.model;

import mvp.android.com.br.androidmvppoc.mvp.presenter.ProfilePresenter;
import mvp.android.com.br.androidmvppoc.network.RepositoryService;

public class ProfileModel implements ProfileModelImpl {

    ProfilePresenter presenter;

    public ProfileModel(ProfilePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getMyProfile() {
        RepositoryService.getInstance(presenter).getMyProfile();
    }
}
