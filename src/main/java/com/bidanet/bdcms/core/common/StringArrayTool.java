package com.bidanet.bdcms.core.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * id字符串，切割与组合
 */
public class StringArrayTool {

    /**
     * 排序后的，集合组合
     * @param ids
     * @return
     */
    public static String ids2StringSort(List<Long> ids){
        Collections.sort(ids);
        return ids2String(ids);
    }

    /**
     * 数组转字符串
     * @param ids 数组
     * @param sp 前缀
     * @return
     */
    public static String ids2String(List<Long> ids,String sp) {
        StringBuilder sb=new StringBuilder("");
        if (ids!=null){
            for (Object id : ids) {
                sb.append(id).append(sp);
            }
        }
        return sb.toString();
    }


    /**
     *数组转字符串,默认后缀 ,
     * @param ids 数组
     * @return
     */
    public static String ids2String(List<Long> ids){
        return ids2String(ids,",");
    }

    /**
     * 字符串转id数组，排序
     * @param idsStr
     * @return 数组
     */
    public static List<Long> string2IdsSort(String idsStr){
        ArrayList<Long> list = string2Ids(idsStr);
        Collections.sort(list);
        return list;
    }


    /**
     *字符串转id数组，
     * @param idsStr
     * @param sp
     * @return
     */
    public static ArrayList<Long> string2Ids(String idsStr,String sp) {
        ArrayList<Long> list = new ArrayList<>();
        if (idsStr!=null&&!idsStr.isEmpty()){
            String[] split = idsStr.split(sp);
            for (String s : split) {
                try {
                    long l = Long.parseLong(s);
                    list.add(l);
                }catch (Exception e){

                }
            }

        }
        return list;
    }

    /**
     * 字符串转换成数组
     * @param idsStr
     * @return
     */
    public static ArrayList<Long> string2Ids(String idsStr){
        return string2Ids(idsStr,",");
    }
}
