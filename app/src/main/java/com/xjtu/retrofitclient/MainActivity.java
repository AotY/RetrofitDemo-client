package com.xjtu.retrofitclient;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xjtu.retrofitclient.bean.User;
import com.xjtu.retrofitclient.common.CommonResponse;
import com.xjtu.retrofitclient.databinding.ActivityMainBinding;
import com.xjtu.retrofitclient.service.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final String BASE_URL = "http://192.168.1.103:8877/";

    private ActivityMainBinding mainBinding;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initRetrofit();
        initView();
    }

    private void initRetrofit() {
        //

        Retrofit userRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory() //Add a call adapter factory for supporting service method return types other than {@link
                .build();

        userService = userRetrofit.create(UserService.class);
    }


    private void initView() {

        // get all
        mainBinding.btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userService.getUsers().enqueue(new Callback<CommonResponse<List<User>>>() {
                    @Override
                    public void onResponse(Call<CommonResponse<List<User>>> call, Response<CommonResponse<List<User>>> response) {
                        Log.d("MainActivity", "response -------------->" + response.body());
                        mainBinding.setResponse(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<CommonResponse<List<User>>> call, Throwable t) {
                        Log.d("MainActivity", "Throwable -------------->" + t.getMessage());
                    }
                });
            }
        });

        // get one
        mainBinding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = 1;
                userService.getUserById(id).enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        Log.d("MainActivity", "response -------------->" + response.body());
                        mainBinding.setResponse(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {

                    }
                });

            }
        });


        // post
        mainBinding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("无法长大", 30);
//                RequestBody requestBody = RequestBody.cre

                userService.addUser(user.getName(), user.getAge()).enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        Log.d("MainActivity", "response -------------->" + response.body());
                        mainBinding.setResponse(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {

                    }
                });
            }
        });


        // put
        mainBinding.btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(1, "无法长大。", 31);
                userService.updateUser(user.getId(), user.getName(), user.getAge()).enqueue(new Callback<CommonResponse<User>>() {
                    @Override
                    public void onResponse(Call<CommonResponse<User>> call, Response<CommonResponse<User>> response) {
                        Log.d("MainActivity", "response -------------->" + response.body());
                        mainBinding.setResponse(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<CommonResponse<User>> call, Throwable t) {

                    }
                });
            }
        });


        //delete

        mainBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = 1;
                userService.deleteUser(id).enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        Log.d("MainActivity", "response -------------->" + response.body());
                        mainBinding.setResponse(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
}
