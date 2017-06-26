package com.bidanet.bdcms.core;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Properties;

/**
 * Created by xuejike on 2017/6/22.
 */

@ConfigurationProperties("bd.core")
public class CoreProperties {


    /**
     * 是否开启
     */
    protected boolean enable;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
