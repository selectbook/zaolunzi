package net.zaolunzi.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.zaolunzi.framework.helper.BeanHelper;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpInInterceptor;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpPostStreamInterceptor;
import org.apache.cxf.jaxrs.provider.jsonp.JsonpPreStreamInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharingFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * REST 助手类
 * @Author: SelectBook
 * @Date: 2022/5/22 16:51
 */
public class RestHelper {
    private static final List<Object> providerList = new ArrayList<Object>();
    private static final List<Interceptor<? extends Message>> inInterceptorList = new ArrayList<Interceptor<? extends Message>>();
    private static final List<Interceptor<? extends Message>> outInterceptorList = new ArrayList<Interceptor<? extends Message>>();
    
    static {
        // 添加 JSON Provider
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        providerList.add(jsonProvider);
        // 添加 Logging Interceptor
        if (RestConfig.isLog()) {
            LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
            inInterceptorList.add(loggingInInterceptor);
            LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
            outInterceptorList.add(loggingOutInterceptor);
        }
        // 添加 JSONP Interceptor
        if (RestConfig.isJsonp()) {
            JsonpInInterceptor jsonpInInterceptor = new JsonpInInterceptor();
            jsonpInInterceptor.setCallbackParam(RestConfig.getJsonpFunction());
            inInterceptorList.add(jsonpInInterceptor);
            JsonpPreStreamInterceptor jsonpPreStreamInterceptor = new JsonpPreStreamInterceptor();
            outInterceptorList.add(jsonpPreStreamInterceptor);
            JsonpPostStreamInterceptor jsonpPostStreamInterceptor = new JsonpPostStreamInterceptor();
            outInterceptorList.add(jsonpPostStreamInterceptor);
        }
        // 添加 CORS Provider
        if (RestConfig.isCors()) {
            CrossOriginResourceSharingFilter corsProvider = new CrossOriginResourceSharingFilter();
            corsProvider.setAllowOrigins(RestConfig.getCorsOriginList());
            providerList.add(corsProvider);
        }
    }
    
    // 发布 REST 服务
    public static void publishService(String wadl, Class<?> resourceClass) {
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress(wadl);
        factory.setResourceClasses(resourceClass);
        factory.setResourceProvider(resourceClass, new SingletonResourceProvider(BeanHelper.getBean(resourceClass)));
        factory.setProviders(providerList);
        factory.setInInterceptors(inInterceptorList);
        factory.setOutInterceptors(outInterceptorList);
        factory.create();
    }
    
    // 创建 REST 客户端
    public static <T> T createClient(String wadl, Class<? extends T> resourceClass) {
        return JAXRSClientFactory.create(wadl, resourceClass, providerList);
    }
}
