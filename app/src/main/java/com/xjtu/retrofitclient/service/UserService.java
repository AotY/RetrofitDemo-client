package com.xjtu.retrofitclient.service;

import com.xjtu.retrofitclient.bean.User;
import com.xjtu.retrofitclient.common.CommonResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by LeonTao on 2018/1/21.
 */

public interface UserService {
    @GET("user/list")
    Call<CommonResponse<List<User>>> getUsers(); //@Path("user") String user @Field

    @GET("user/{id}")
    Call<CommonResponse> getUserById(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("user/add")
//    Call<CommonResponse> addUser(@Body User user);
    Call<CommonResponse> addUser(@Field("name") String name, @Field("age") Integer age);

    @FormUrlEncoded
    @PUT("user/update")
//    Call<CommonResponse<User>> updateUser(@Body User user);
    Call<CommonResponse<User>> updateUser(@Field("id") Integer id, @Field("name") String name, @Field("age") Integer age);

    @DELETE("user/delete/{id}")
    Call<CommonResponse> deleteUser(@Path("id") Integer id);
}
