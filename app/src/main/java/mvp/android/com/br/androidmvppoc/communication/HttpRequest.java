package mvp.android.com.br.androidmvppoc.communication;

import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;

public class HttpRequest extends BaseAPIClient {

    public HttpRequest(BasePresenterImpl presenter) {
        super(presenter);
    }

    @Override
    void onStart() {
        getPresenter().showProgressBar(true);
    }

    @Override
    void onEnd() {
        getPresenter().showProgressBar(false);
    }
}
