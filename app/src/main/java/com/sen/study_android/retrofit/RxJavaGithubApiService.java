package com.sen.study_android.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface RxJavaGithubApiService {
    @GET("users/{name}/repos")
    Observable<ResponseBody> searchRepoInfo(@Path("name") String name);
}
