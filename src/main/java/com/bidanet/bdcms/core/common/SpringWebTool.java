package com.bidanet.bdcms.core.common;


import com.google.common.base.Strings;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SpringMVC，web相关的工具类
 */
public class SpringWebTool {

    protected static String urlExtension = ".do";

    public static String getUrlExtension() {
        return urlExtension;
    }

    public static void setUrlExtension(String urlExtension) {
        SpringWebTool.urlExtension = urlExtension;
    }

    protected static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal=new ThreadLocal<>();
    protected static ThreadLocal<HttpServletResponse> httpServletResponseThreadLocal=new ThreadLocal<>();
    public static HttpSession getSession(){
//        Executors.newCachedThreadPool()
        HttpServletRequest request = getRequest();
        //得到session
        return request.getSession();

    }

    public static HttpServletRequest getRequest() {
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //得到request

        return httpServletRequestThreadLocal.get();
    }
    public static HttpServletResponse getResponse(){
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return httpServletResponseThreadLocal.get();
    }
    public static ServletContext getServletContext(){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }
    public static String getRealPath(String path){
        return getServletContext().getRealPath(path);
    }

    public static String getWebRootUrl(){
        HttpServletRequest request = getRequest();

        String host = request.getScheme()+
                "://"+request.getServerName()+
                ":"+request.getServerPort();
        return host+"/"+request.getContextPath()+"/";
    }
    public static <T> T getBean(Class<T> tClass){
        return ContextLoader.getCurrentWebApplicationContext().getBean(tClass);
    }
    public static <T> T getBean(String name,Class<T> tClass){
        return ContextLoader.getCurrentWebApplicationContext().getBean(name,tClass);
    }

    public static boolean isAjax(){

        String requestType = getRequest().getHeader("X-Requested-With");
        if (requestType == null || "".equals(requestType)){
            return false;
        }else{
            return true;
        }
    }

    public static void  redirect(String url,Object... param){
        redirectUrl(buildUrl(url,param));
    }
    public static void redirectUrl(String url){
        try {
            getResponse().sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRequestResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletRequestThreadLocal.set(httpServletRequest);
        httpServletResponseThreadLocal.set(httpServletResponse);
    }




    public static String buildUrl(String urlStr,Object... param){
        StringBuffer url = new StringBuffer();

        HttpServletRequest request = SpringWebTool.getRequest();

        String host = request.getScheme()+
                "://"+request.getServerName()+
                ":"+request.getServerPort();
        url.append(host);

        urlStr=urlStr.trim();
        String requestURI = request.getRequestURI();
        if (Strings.isNullOrEmpty(urlStr)){
            url.append(requestURI);
        }else{


            if (urlStr.charAt(0)!='/'){
                int i = requestURI.lastIndexOf("/");
                String substring = requestURI.substring(0, i);
                url.append(substring).append("/");
            }else{
                String contextPath = request.getContextPath();
                url.append(contextPath);
            }
            buildParam(urlStr, url, param);
        }


        return url.toString();
    }

    private static void buildParam(String urlStr, StringBuffer url, Object[] param) {


        Pattern pattern = Pattern.compile("\\?");
        Matcher matcher = pattern.matcher(urlStr);
//            找到第一个？
        if (matcher.find()) {
            matcher.appendReplacement(url, urlExtension + "?");

            if (param!=null){
                int childIndex = 0;
                while (matcher.find()) {
                    if (childIndex < param.length) {
                        if (param[childIndex]==null){
                            matcher.appendReplacement(url,"");
                        }else{
                            matcher.appendReplacement(url, String.valueOf(param[childIndex]));
                        }

                        childIndex++;
                    } else {
                        break;
                    }
                }
                matcher.appendTail(url);
            }

        }else{
            url.append(urlStr).append(urlExtension);
        }
    }

}
