package cn.zaolunzi.feige.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 服务发布自定义标签
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public class FeigeRemoteServiceNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("service", new ProviderFactoryBeanDefinitionParser());
    }
}
