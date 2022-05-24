package cn.zaolunzi.feige.cluster.impl;

import cn.zaolunzi.feige.cluster.ClusterStrategy;
import cn.zaolunzi.feige.helper.IPHelper;
import cn.zaolunzi.feige.model.ProviderService;

import java.util.List;

/**
 * 软负载哈希算法实现
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public class HashClusterStrategyImpl implements ClusterStrategy {

    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        //获取调用方ip
        String localIP = IPHelper.localIp();
        //获取源地址对应的hashcode
        int hashCode = localIP.hashCode();
        //获取服务列表大小
        int size = providerServices.size();

        return providerServices.get(hashCode % size);
    }
}
