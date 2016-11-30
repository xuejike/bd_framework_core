package com.bidanet.bdcms.core.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/30.
 */
public class CommonTool {





    protected static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    protected static SimpleDateFormat simpleDateTimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String nowDate(){
        return simpleDateFormat.format(new Date());
    }
    public static String stringDateTime(Long time){
        if (time==null){
            return "";
        }
        return simpleDateTimeFormat.format(new Date(time));
    }
    public static String stringDate(Long time){
        if (time==null){
            return "";
        }
        return simpleDateFormat.format(new Date(time));
    }


    /**
     * 计算金额，小于零则取零
     * @param brokerage
     * @param scale
     * @return
     */
    public static Float compute(Float brokerage,Float scale){
        if (brokerage==null||scale==null){
            return 0F;
        }
        float v = brokerage * scale * 100;
        float price = Float.parseFloat(String.valueOf((Math.floor(v) / 100)));
        return price>0?price:0;
    }

}
