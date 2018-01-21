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

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();
    }


    private void initView() {
        mainBinding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Retrofit userRetrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.103:8877/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserService userService = userRetrofit.create(UserService.class);

//                try {
//                    Response<CommonResponse<List<User>>> commonResponse = userService.getUsers().execute();
//                    Log.d("MainActivity", "commonResponse -------------->" + commonResponse.body());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
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

//                Call<CommonResponse<List<User>>> commonResponseCall = userService.getUsers();
//                Log.d("MainActivity", "commonResponseCall -------------->" + commonResponseCall);
//
//                commonResponseCall.enqueue(new Callback<CommonResponse<List<User>>>() {
//                    @Override
//                    public void onResponse(Call<CommonResponse<List<User>>> call, Response<CommonResponse<List<User>>> response) {
//                        Log.d("MainActivity", "commonResponse -------------->" + response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<CommonResponse<List<User>>> call, Throwable t) {
//
//                    }
//                });

            }
        });

    }
}
