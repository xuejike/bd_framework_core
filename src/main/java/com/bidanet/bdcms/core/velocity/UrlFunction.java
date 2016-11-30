package com.bidanet.bdcms.core.velocity;



import com.bidanet.bdcms.core.common.SpringWebTool;
import com.google.common.base.Strings;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL生成器
 * Created by xuejike on 2015/11/2.
 */

public class UrlFunction extends Directive {

    @Override
    public String getName() {
        return "U";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter internalContextAdapter, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        //使用方法 #U('地址',参数)  #U('/Index/index')  =>  项目地址/Index/index.vm
//                                #U('index')   =>当前地址/index.vm
//        #U('/Index/index?p=?&p1=?',1,2)  => 项目地址/Index/index.vm?p=1&p1=2
        int i = node.jjtGetNumChildren();
        if (i>0){
            String urlStr = (String) node.jjtGetChild(0).value(internalContextAdapter);
            Object[] params = new Object[i - 1];
            for (int j = 1; j < i; j++) {
                params[j-1]= node.jjtGetChild(j).value(internalContextAdapter);
            }
            writer.write(buildUrl(urlStr,params));
        }else{
            writer.write(buildUrl(""));
        }


        return true;
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
            matcher.appendReplacement(url, ".do?");

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
            url.append(urlStr).append(".do");
        }
    }
}
