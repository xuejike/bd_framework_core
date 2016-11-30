package com.bidanet.bdcms.core.common;

import com.bidanet.bdcms.core.exception.CheckException;
import com.thoughtworks.xstream.XStream;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/7/3.
 */

public class XmlTool {
//    XStream xstream = new XStream();
//
//
//    @PostConstruct
//    protected void init(){
//
//    }
    public String toXml(Object obj){
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        return xstream.toXML(obj);
    }
    public Object fromXml(String xml,String error,Class ... classes){
        XStream xstream = new XStream();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.processAnnotations(classes);
        try{
            Object o = xstream.fromXML(xml);

            return o;
        }catch (Exception e){
            if (error==null){
                return null;
            }else{
                throw new CheckException(error);
            }
        }
    }
    public Object fromXml(String xml, Class ... classes){
        return fromXml(xml,null, classes);
    }
}
