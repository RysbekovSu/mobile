package com.example.students.remote_data;


import com.example.students.model.Apply;
import com.example.students.model.ArticelModel;
import com.example.students.model.CurrentUser;
import com.example.students.model.LoginResponse;
import com.example.students.model.Podcast;
import com.example.students.model.Teacher;
import com.example.students.model.TestQuestion;
import com.example.students.model.User;
import com.example.students.model.WordModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("api/v1/teacher/")
    Call<List<Teacher>> getTeacher();

    @POST("api/v1/apply/create/")
    Call<Apply> apply(@Body Apply apply);

    @POST("api/register/")
    Call<User> registrationNewUser(@Body User user);

    @POST("api/login/")
    Call<LoginResponse> checkLoginUser(@Body CurrentUser currentUser);

    @GET("api/v1/tests/")
    Call<List<TestQuestion>> getTestQuestion();

    @GET("api/v1/words/")
    Call<List<WordModel>> getWords();

    @GET("api/v1/article/")
    Call<List<ArticelModel>> getArticels();

    @GET("api/v1/podcast/")
    Call<List<Podcast>> getPodcasts();
    @POST("api/v1/auth/logout")Call<User> makeLogOutUser();
}
