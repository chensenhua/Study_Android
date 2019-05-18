package com.sen.study_android.retrofit;


import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

public class RetrofitManager {

    private static RetrofitManager mInstance = new RetrofitManager();
    private Retrofit retrofit;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static RetrofitManager getInstance() {
        return mInstance;
    }

    public void testGet() {
        GithubApiService githubApiService = retrofit.create(GithubApiService.class);
        Call<ResponseBody> call = githubApiService.searchRepoInfo("changmu175");
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testRxJavaGet(){
        RxJavaGithubApiService rxJavaGithubApiService=retrofit.create(RxJavaGithubApiService.class);
        Observable<ResponseBody> responseBodyObservable= rxJavaGithubApiService.searchRepoInfo("changmu175");

    }
}
