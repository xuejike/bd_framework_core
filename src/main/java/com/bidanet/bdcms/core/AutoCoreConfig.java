package com.bidanet.bdcms.core;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.bidanet.bdcms.core.interceptor.RequestInterceptor;
import com.bidanet.bdcms.core.messageConverter.FileOutputMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by xuejike on 2017/6/26.
 */
@Configuration
@ConfigurationProperties
@ConditionalOnClass(CoreProperties.class)
@EnableConfigurationProperties(CoreProperties.class)
@ConditionalOnProperty(prefix = "bd.core",value = "enabled",havingValue = "true")
public class AutoCoreConfig extends WebMvcConfigurerAdapter{


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setCharset(Charset.forName("UTF-8"));
        fastJsonHttpMessageConverter.setFeatures(SerializerFeature.DisableCircularReferenceDetect);
        converters.add(new FileOutputMessageConverter());
        converters.add(new FastJsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}
