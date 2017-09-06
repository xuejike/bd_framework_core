package com.bidanet.bdcms.core.controller;

import com.bidanet.bdcms.core.bean.AjaxCallBack;
import com.bidanet.bdcms.core.exception.CheckException;

/**
 * Created by xuejike on 2016-04-15.
 */

public class BaseController {



    protected void errorMsg(String msg){
        throw new CheckException(msg);
    }
    protected void checkNull(Object obj,String msg){
        if (obj==null){
            errorMsg(msg);
        }
    }





}
