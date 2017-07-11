package com.bidanet.bdcms.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by xuejike on 2017/7/11.
 */

public class BdApplication {

    protected static ApplicationContext applicationContext;

    private BdApplication(){

    }
    public static BdApplication init(ApplicationContext app){
        applicationContext=app;
        return new BdApplication();
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
