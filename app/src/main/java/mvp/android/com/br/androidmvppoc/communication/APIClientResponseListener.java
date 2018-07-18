package mvp.android.com.br.androidmvppoc.communication;

import retrofit2.Call;

/**
 * Created by rafael.alves on 16/01/2018.
 */

public interface APIClientResponseListener<T> {
    void onSuccess(T obj);
    void onError(Call<T> obj, Throwable t);
}
