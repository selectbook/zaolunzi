package cn.zaolunzi.feige.zookeeper;


import cn.zaolunzi.feige.model.ProviderService;

import java.util.List;
import java.util.Map;

/**
 * 服务端注册中心接口
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public interface IRegisterCenter4Provider {


    /**
     * 服务端将服务提供者信息注册到zk对应的节点下
     *
     * @param serviceMetaData
     */
    public void registerProvider(final List<ProviderService> serviceMetaData);


    /**
     * 服务端获取服务提供者信息
     * <p/>
     * 注:返回对象,Key:服务提供者接口  value:服务提供者服务方法列表
     *
     * @return
     */
    public Map<String, List<ProviderService>> getProviderServiceMap();


}
