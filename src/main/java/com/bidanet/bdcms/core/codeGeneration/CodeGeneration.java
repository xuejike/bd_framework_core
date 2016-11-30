package com.bidanet.bdcms.core.codeGeneration;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by xuejike on 2016-05-25.
 */
public class CodeGeneration {

    private static String daoPath;
    private static String daoImplPath;
    private static String servicePath;
    private static String serviceImplPath;

    private static String outPath=System.getProperty("user.dir") + "\\src\\main\\java";
    private static String rootPath = "com/bidanet/bdcms/";

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

//        build(CourseChapter.class,daoPath, daoImplPath, servicePath, serviceImplPath);
    }

    public static void build(Class c,String daoPath,String daoImplPath,String servicePath,String serviceImplPath){
        String packName = c.getPackage().getName();
        String property = System.getProperty("java.class.path");

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        buildTpl("codeTpl/dao.vm",c, "dao", ve,"Dao.java");

        buildTpl("codeTpl/daoImpl.vm",c, "dao/impl", ve,"DaoImpl.java");
        buildTpl("codeTpl/Service.vm",c, "service", ve,"Service.java");
        buildTpl("codeTpl/ServiceImpl.vm",c, "service/impl", ve,"ServiceImpl.java");
//啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦222

    }

    private static void buildTpl(String tpl,Class c, String path, VelocityEngine ve,String out) {
        /**
         * 代码生成器...........
         * 1111111
         * 2222222
         */

        Template t = ve.getTemplate(tpl);
        VelocityContext ctx = new VelocityContext();
        path=rootPath+path;
        ctx.put("packName", path.replaceAll("/","."));
        ctx.put("root",rootPath);
        ctx.put("classPackName", c.getName());
        ctx.put("className",c.getSimpleName());

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        File file = new File(outPath+"/"+path+"/"+c.getSimpleName()+out);
        if (file.exists()){
            System.out.println("文件已存在");
        }else{
            try {
                FileUtils.writeStringToFile(file,sw.toString(),"UTF-8");
                System.out.println("生成成功:->"+c.getSimpleName()+"->"+path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
