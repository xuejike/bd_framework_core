package com.bidanet.bdcms.core.common;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xuejike on 2014/12/20.
 */
@SuppressWarnings("unchecked")
public class ReflectUtil {
    /**
     * 获取泛型的 Class 对象
     * @param obj
     * @param <T>
     * @return
     */
        public static <T> Class getGenericClass(Object obj){
            ParameterizedType type = (ParameterizedType) obj.getClass().getGenericSuperclass();
            return (Class<T>) type.getActualTypeArguments()[0];
        }

    /**
     * 创建一个Class 的对象
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getClassInstance(Class<T> tClass){
            try {
               return tClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }


}
