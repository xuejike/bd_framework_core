package com.bidanet.bdcms.core.bean;

/**
 * Created by Administrator on 2016/6/28.
 */
public class Result {
    /**
     * 请求成功
     */
    public static final int STATUS_OK=200;
    /**
     * 请求失败
     */
    public static final int STATUS_ERROR=300;
    /**
     * 未登录
     */
    public static final int STATUS_TIMEOUT=301;

    /**
     *请输入验证码
     */
    public static final int STATUS_INPUT_IMG_CODE=302;


    protected int statusCode;
    protected String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Result() {
    }


}
