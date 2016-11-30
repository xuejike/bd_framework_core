package com.bidanet.bdcms.core.driver.cache;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public interface Cache {
    void set(String key, String val);

    void set(String key, Object val);

    void set(String key, String val, int seconds);

    void set(String key, Object val, int seconds);

    void expire(String key, int seconds);

    String get(String key);
    List<String> get(String... key);

    <T> T get(String key, Class<T> tClass);


    <T> List<T> getArray(String key, Class<T> tClass);


    void delete(String... key);

    void clean();
}
