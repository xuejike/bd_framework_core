package com.bidanet.bdcms.core.dao.tool;

import com.bidanet.bdcms.core.dao.tool.proxy.MapListProxy;
import com.bidanet.bdcms.core.dao.tool.proxy.MapObjectProxy;
import org.springframework.cglib.proxy.Enhancer;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xuejike on 2017/3/10.
 */
public class MainTest {
    public static void main(String[] arg){
        HashMap<String, Object> map = new HashMap<>();
        MapObjectProxy proxy = new MapObjectProxy(map);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestClase.class);
        enhancer.setCallback(proxy);


        TestClase o = (TestClase)enhancer.create();
        o.setName("xxx");
        o.setOpen(false);


        HashMap<String, List<Object>> mapList = new HashMap<>();
        MapListProxy mapListProxy = new MapListProxy(mapList);
        enhancer.setCallback(mapListProxy);
        o.setName("xxx");

    }

}
