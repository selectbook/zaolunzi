package net.zaolunzi.framework.proxy;

/**
 * 代理接口
 *
 * @Author: SelectBook
 * @Date: 2022/5/20 2:46
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}