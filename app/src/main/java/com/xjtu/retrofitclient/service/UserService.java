package com.xjtu.retrofitclient.service;

import com.xjtu.retrofitclient.bean.User;
import com.xjtu.retrofitclient.common.CommonResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LeonTao on 2018/1/21.
 */

public interface UserService {
    @GET("user/list")
    Call<CommonResponse<List<User>>> getUsers(); //@Path("user") String user @Field

}
