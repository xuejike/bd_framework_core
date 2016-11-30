package com.bidanet.bdcms.core.bean;

/**
 * API结果集
 */
public class ApiResult<T> extends Result {
    protected T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static<T> ApiResult<T> success(T data){
        return new ApiResult<>(STATUS_OK,data);
    }

    public static ApiResult error(String msg){
        return new ApiResult(STATUS_ERROR,msg);
    }

    public static ApiResult timeout(){
        return new ApiResult(STATUS_TIMEOUT,"未登录");
    }


    public ApiResult() {
    }

    public ApiResult(int statusCode, String message) {
        super(statusCode, message);
    }

    public ApiResult(int statusCode, T data) {
        this.statusCode=statusCode;
        this.data = data;
    }
}
