package com.bidanet.bdcms.core.dao.tool.proxy;

import com.bidanet.bdcms.core.dao.tool.PropertyNameTool;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public class MapObjectProxy implements MethodInterceptor {
    protected Map<String,Object> map;

    public MapObjectProxy(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
//        System.out.println(PropertyNameTool.getProperty(method.getName()));
        Object o1 = methodProxy.invokeSuper(o, objects);
//        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
        String methodName = method.getName();
        if (PropertyNameTool.isSeter(methodName)){
            map.put(PropertyNameTool.getProperty(methodName),objects[0]);
        }
        return o1;

    }
}
