package cn.zaolunzi.feige.cluster.impl;

import cn.zaolunzi.feige.cluster.ClusterStrategy;
import cn.zaolunzi.feige.model.ProviderService;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * 软负载随机算法实现
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public class RandomClusterStrategyImpl implements ClusterStrategy {
    @Override
    public ProviderService select(List<ProviderService> providerServices) {
        int MAX_LEN = providerServices.size();
        int index = RandomUtils.nextInt(0, MAX_LEN - 1);
        return providerServices.get(index);
    }

}
