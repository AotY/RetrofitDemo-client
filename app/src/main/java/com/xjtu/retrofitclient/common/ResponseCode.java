package com.xjtu.retrofitclient.common;

import java.io.Serializable;

/**
 * Created by LeonTao on 2018/1/21.
 */

public enum ResponseCode implements Serializable {

    success(0, "成功"),
    failed(1, "失败");

    private int code;

    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
