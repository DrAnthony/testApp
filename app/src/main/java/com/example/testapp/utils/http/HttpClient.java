package com.example.testapp.utils.http;

import com.example.testapp.entity.ResponseEntity;
import com.example.testapp.entity.School;
import com.example.testapp.entity.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HttpClient {
    class Builder{
        public static HttpClient getHttpClient(){
            return HttpUtils.getInstance().getHttpClient(HttpClient.class);
        }
    }
    @POST("/login/student")
    Call<ResponseEntity<Student>> login(@Body Student student);
    @DELETE("/logout/user")
    Call<ResponseEntity> logout();
    @GET("/school/{id}")
    Call<ResponseEntity<List<School>>> getSchool(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("/code")
    Call<ResponseEntity> getCode(@Field("tel") String tel);
}
