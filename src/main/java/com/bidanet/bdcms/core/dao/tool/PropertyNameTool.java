package com.bidanet.bdcms.core.dao.tool;

/**
 * Created by xuejike on 2017/3/10.
 */
public class PropertyNameTool {
    public static String getProperty(String geterSeter){
        checkString(geterSeter);
        boolean is = geterSeter.startsWith("is");
        String propertyName;
        if (is){
            propertyName=geterSeter.substring(2);
        }else{
            propertyName=geterSeter.substring(3);
        }
        if (Character.isUpperCase(propertyName.charAt(0))){
            char[] chars = propertyName.toCharArray();
            chars[0]=Character.toLowerCase(chars[0]);
            propertyName=new String(chars);
        }
        return propertyName;
    }

    private static void checkString(String geterSeter) {
        if (geterSeter==null||"".equals(geterSeter)){
            throw new PropertyNameException("参数不能为Null 或者空字符串");
        }
    }

    public static boolean isSeter(String methodName){
        checkString(methodName);
        return methodName.startsWith("set");
    }
    public static boolean isGeter(String methodName){
        checkString(methodName);
        if (methodName.startsWith("is")){
            return true;
        }
        return methodName.startsWith("get");
    }
    static class PropertyNameException extends RuntimeException{
        public PropertyNameException(String message) {
            super(message);
        }
    }
}
