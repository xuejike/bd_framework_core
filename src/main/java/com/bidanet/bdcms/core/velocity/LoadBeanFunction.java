package com.bidanet.bdcms.core.velocity;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Block;
import org.apache.velocity.runtime.parser.node.Node;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.Writer;

/**
 * 加载Service 相关bean
 */
public class LoadBeanFunction extends Block {
    @Override
    public String getName() {
        return "LoadBean";
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        int numChildren = node.jjtGetNumChildren();
        WebApplicationContext springContext = ContextLoader.getCurrentWebApplicationContext();
        for (int i = 0; i < numChildren - 1; i++) {
            String beanName = (String) node.jjtGetChild(i).value(context);
            try{
                Object bean = springContext.getBean(beanName);
                context.put(beanName,bean);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
        render(context, writer);
        return true;
    }
}
