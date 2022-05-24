package cn.zaolunzi.feige.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 服务引入自定义标签
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public class FeigeRemoteReferenceNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("reference", new RevokerFactoryBeanDefinitionParser());
    }
}
