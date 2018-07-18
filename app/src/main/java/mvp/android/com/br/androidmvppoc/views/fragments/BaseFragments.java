package mvp.android.com.br.androidmvppoc.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewFragmentImpl;
import mvp.android.com.br.androidmvppoc.mvp.base.BaseViewImpl;

public abstract class BaseFragments<T extends BasePresenterImpl> extends Fragment implements BaseViewFragmentImpl {

    protected abstract T newPresenter();

    abstract int getLayoutId();

    abstract void myOnCreate(View view, Bundle savedInstanceState);

    private T presenter;

    public View mView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mView = inflater.inflate(getLayoutId(),
                container, false);
        ButterKnife.bind(this, mView);
        presenter = newPresenter();
        presenter.setView(this);
        myOnCreate(mView, savedInstanceState);
        return mView;
    }


    public View findViewById(int resourceId) {
        return mView.findViewById(resourceId);
    }

    @Override
    public void showProgressBar(int visibilidade) {

    }

    @Override
    public void runOnUiThread(Runnable action) {
        if (getActivityBase() != null) {
            getActivityBase().runOnUiThread(action);
        }
    }

    @Override
    public void gotoNextScreen(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    @Override
    public void gotoNextScreen(Class<?> cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void gotoNextScreen(Class<?> cls, String dataBundle, String keyBundle) {
        Intent intent = new Intent(getActivity(), cls);
        Bundle bundle = new Bundle();
        bundle.putString(keyBundle, dataBundle);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void gotoNextScreen(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void gotoNextScreen(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, requestCode);
    }

    public T getPresenter() {
        return presenter;
    }

    @Override
    public BaseViewFragmentImpl getFragment() {
        return null;
    }

    protected BaseViewImpl getActivityBase() {
        return ((BaseViewImpl) getActivity());
    }
}
