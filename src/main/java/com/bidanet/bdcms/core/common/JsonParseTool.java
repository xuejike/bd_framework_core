package com.bidanet.bdcms.core.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bidanet.bdcms.core.exception.CheckException;

import java.util.List;

/**
 *json 转换类库
 */
public class JsonParseTool {
    /**
     * 把json转换成数组
     * @param json  json字符串
     * @param tClass 目标对象
     * @param errorMsg  转换失败消息
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> tClass, String errorMsg){
        try{
            return JSON.parseArray(json,tClass);
        }catch (Exception e){
            throw new CheckException(errorMsg);
        }
    }

    /**
     * 把json转换成数组
     * @param json json字符串
     * @param errorMsg 转换失败消息
     * @return
     */
    public static <T>  JSONArray parseArray(String json, String errorMsg){
        try{
            return JSON.parseArray(json);
        }catch (Exception e){
            e.printStackTrace();
            throw new CheckException(errorMsg);
        }
    }

    /**
     * 把json转换成对象
     * @param json json字符串
     * @param error 转换失败消息
     * @return
     */
    public static <T>  JSONObject parseObject(String json,String error){
        try {
            return JSON.parseObject(json);
        }catch (Exception e){
            e.printStackTrace();
            throw new CheckException(error);
        }
    }

    /**
     * 把json转换成对象
     * @param json json字符串
     * @param tClass 目标对象
     * @param error 转换失败消息
     * @return
     */
    public static <T>  T parseObject(String json,Class<T> tClass,String error){
        try {
            return JSON.parseObject(json,tClass);
        }catch (Exception e){
            e.printStackTrace();
            throw new CheckException(error);
        }
    }


    /**
     * 把对象转换成 JSON字符串
     * @param obj  数据对象
     * @return
     */
    public static String toJson(Object obj){
        return JSON.toJSONString(obj);
    }

}
