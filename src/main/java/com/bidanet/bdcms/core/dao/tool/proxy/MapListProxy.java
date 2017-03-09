package com.bidanet.bdcms.core.dao.tool.proxy;

import com.bidanet.bdcms.core.dao.tool.PropertyNameTool;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public class MapListProxy implements MethodInterceptor {
    protected Map<String,List<Object>> mapList;

    public MapListProxy(Map<String, List<Object>> mapList) {
        this.mapList = mapList;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object o1 = methodProxy.invokeSuper(o, objects);

//        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
        String methodName = method.getName();
        if (PropertyNameTool.isSeter(methodName)){
            String property = PropertyNameTool.getProperty(methodName);
            List<Object> list = mapList.get(property);
            if (list==null){
                list= new ArrayList<Object>();
                mapList.put(property,list);
            }
            list.add(objects[0]);
        }
        return o1;
    }
}
