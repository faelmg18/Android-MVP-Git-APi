package mvp.android.com.br.androidmvppoc.communication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import mvp.android.com.br.androidmvppoc.communication.dataserializer.DataSerializer;
import mvp.android.com.br.androidmvppoc.mvp.base.BasePresenterImpl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rafael.alves on 16/07/2018.
 */

public abstract class BaseAPIClient {

    protected static volatile Retrofit retrofit = null;

    abstract void onStart() throws Exception;

    abstract void onEnd() throws Exception;

    private WeakReference<BasePresenterImpl> presenter;
    static String baseUrl = "https://api.github.com/";

    public BaseAPIClient(BasePresenterImpl presenter) {
        this.presenter = new WeakReference<>(presenter);
        initClient();
    }

    private void initClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        interceptor.setLevel
                (HttpLoggingInterceptor.Level.BODY);
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = getHeaders(original);
                return chain.proceed(request);
            }
        });


        OkHttpClient client = builder.build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    protected Request getHeaders(Request original) {
        return original.newBuilder()
                .header("Content-type", "application/json")
                .method(original.method(), original.body())
                .build();
    }

    protected <T> void execute(final Call<T> call, final APIClientResponseListener listener) {
        startProcess();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(final Call<T> call, final Response<T> response) {
                Log.d("BaseAPIClient", "BaseAPIClient Success => code = " + response.code() + "");
                if (presenter != null && presenter.get() != null) {
                    presenter.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (response.code() != 200) {
                                listener.onError(call, new Exception("Internal server error " + call.request().body()));
                                return;
                            }

                            listener.onSuccess(response.body());
                        }
                    });
                    finishProcess();
                }
            }

            @Override
            public void onFailure(final Call<T> call, final Throwable t) {
                call.cancel();
                Log.d("BaseAPIClient", "BaseAPIClient Failure, motive is = " + t.getMessage());
                if (presenter != null) {
                    presenter.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError(call, t);
                        }
                    });
                }
                finishProcess();
            }
        });
    }

    private void startProcess() {
        try {
            onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finishProcess() {
        try {
            onEnd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void createBody(String json, CallListener callListener, MediaType mediaType) {
        RequestBody body = RequestBody.create(mediaType, json);
        if (callListener != null) {
            callListener.onBodyCreated(body);
        }
    }

    protected <T> void createBody(T obj, CallListener callListener) {
        String json = DataSerializer.getInstance().toJson(obj);
        createBody(json, callListener, MediaType.parse("application/json; charset=utf-8"));
    }

    protected <T> void createBody(T obj, CallListener callListener, MediaType mediaType) {
        String json = DataSerializer.getInstance().toJson(obj);
        createBody(json, callListener, mediaType);
    }

    public BasePresenterImpl getPresenter() {
        return presenter != null ? presenter.get() : null;
    }

    public abstract class CallListener {
        protected void onBodyCreated(RequestBody body) {
        }
    }

    protected String getBaseUrl() {
        return baseUrl;
    }
}
