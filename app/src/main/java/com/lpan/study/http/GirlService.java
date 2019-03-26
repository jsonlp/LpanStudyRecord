package com.lpan.study.http;

import com.lpan.study.model.GirlData;
import com.lpan.study.model.StudentData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lpan on 2018/5/28.
 */

public interface GirlService {

    @GET("data/福利/{number}/{page}")
    Observable<GirlData> getBeauties(@Path("number") int number, @Path("page") int page);

    @GET("/helloworld/my")
    Observable<StudentData> getStudent();
}
