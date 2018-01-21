package com.xjtu.retrofitclient.common;

import java.io.Serializable;

/**
 * Created by LeonTao on 2018/1/21.
 */

public class CommonResponse<T> implements Serializable {

    private T data;

    private int code;

    private String msg;

    public CommonResponse() {
    }


    public CommonResponse(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static CommonResponse getSuccessResponse(Object data) {
        CommonResponse commonResponse = new CommonResponse(data, ResponseCode.success.getCode(), ResponseCode.success.getMsg());
        return commonResponse;
    }

    public static CommonResponse getFailedResponse(Object data) {
        CommonResponse commonResponse = new CommonResponse(data, ResponseCode.failed.getCode(), ResponseCode.failed.getMsg());
        return commonResponse;
    }

    @Override
    public String toString() {
//        return super.toString();
        return String.format("code: %d, msg: %s%s", code, msg, data.toString());
    }
}
