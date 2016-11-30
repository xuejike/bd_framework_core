package com.bidanet.bdcms.core.driver.cache;

import com.bidanet.bdcms.core.common.JsonParseTool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/1.
 */
public class RedisCache implements Cache {
    protected static Logger logger=Logger.getLogger(RedisCache.class);


    @Autowired
    private JedisPool jedisPool;



    @Override
    public void set(String key, String val){

        exec(j->j.set(key, val));

    }
    @Override
    public void set(String key, Object val) {
        if (val!=null){
            exec(j->j.set(key, JsonParseTool.toJson(val)));
        }
    }

    @Override
    public void set(String key, Object val, int seconds) {
        exec(j->{
            if (key!=null){
                j.set(key, JsonParseTool.toJson(val));
                j.expire(key, seconds);
            }
        });
    }
    public void set(String key, String val, int seconds) {
        exec(j->{
            if (key!=null){
                j.set(key, val);
                j.expire(key, seconds);
            }
        });
    }
    @Override
    public void expire(String key, int seconds){
        exec(j-> j.expire(key, seconds));

    }

    @Override
    public String get(String key){
        final String[] s = {null};
        exec(j-> s[0] =j.get(key));
        return s[0];
    }

    @Override
    public List<String> get(String... key) {

        List<String> list = new ArrayList();
        exec(j->list.addAll(j.mget(key)));
        return list;
    }

    @Override
    public <T> T get(String key,Class<T> tClass) {
        String s = get(key);
        if (s==null) return null;
        return JsonParseTool.parseObject(s,tClass,"数据类型不匹配");
    }
    @Override
    public<T> List<T> getArray(String key, Class<T> tClass){
        String s = get(key);
        if (s==null) return null;
        return JsonParseTool.parseArray(s,tClass,"数据类型不匹配");
    }

    @Override
    public void delete(String... key) {

        exec(j->j.del(key));

    }

    @Override
    public void clean() {
        exec(j->j.flushAll());
    }

    /**
     * 查找
     * @param pattam
     * @return
     */
    public Set<String> keys(String pattam){
        Set<String> keys =new HashSet<>();

        exec(jedis->keys.addAll(jedis.keys(pattam)) );

        return keys;
    }


    public void exec(RedisExec exec){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            exec.exec(jedis);
        }catch (Exception e){
            e.printStackTrace();
            //logInfoService.addErrorLog("redis",e.getMessage());

        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }



    }

    public interface RedisExec{
        void exec(Jedis jedis);
    }


}
