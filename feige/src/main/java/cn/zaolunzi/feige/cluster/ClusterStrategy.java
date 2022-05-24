package cn.zaolunzi.feige.cluster;


import cn.zaolunzi.feige.model.ProviderService;

import java.util.List;

/**
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public interface ClusterStrategy {

    /**
     * 负载策略算法
     *
     * @param providerServices
     * @return
     */
    public ProviderService select(List<ProviderService> providerServices);
}
