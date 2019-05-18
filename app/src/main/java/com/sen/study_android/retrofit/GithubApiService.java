package com.sen.study_android.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GithubApiService {
        @GET("users/{name}/repos")
        Call<ResponseBody> searchRepoInfo(@Path("name") String name);
    }