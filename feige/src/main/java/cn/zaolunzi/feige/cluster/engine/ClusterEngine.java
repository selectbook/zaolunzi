package cn.zaolunzi.feige.cluster.engine;

import avro.shaded.com.google.common.collect.Maps;
import cn.zaolunzi.feige.cluster.ClusterStrategy;
import cn.zaolunzi.feige.cluster.impl.ClusterStrategyEnum;
import cn.zaolunzi.feige.cluster.impl.HashClusterStrategyImpl;
import cn.zaolunzi.feige.cluster.impl.PollingClusterStrategyImpl;
import cn.zaolunzi.feige.cluster.impl.RandomClusterStrategyImpl;
import cn.zaolunzi.feige.cluster.impl.WeightPollingClusterStrategyImpl;
import cn.zaolunzi.feige.cluster.impl.WeightRandomClusterStrategyImpl;

import java.util.Map;

/**
 * 负载均衡引擎
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 23:36
 */
public class ClusterEngine {

    private static final Map<ClusterStrategyEnum, ClusterStrategy> clusterStrategyMap = Maps.newConcurrentMap();

    static {
        clusterStrategyMap.put(ClusterStrategyEnum.Random, new RandomClusterStrategyImpl());
        clusterStrategyMap.put(ClusterStrategyEnum.WeightRandom, new WeightRandomClusterStrategyImpl());
        clusterStrategyMap.put(ClusterStrategyEnum.Polling, new PollingClusterStrategyImpl());
        clusterStrategyMap.put(ClusterStrategyEnum.WeightPolling, new WeightPollingClusterStrategyImpl());
        clusterStrategyMap.put(ClusterStrategyEnum.Hash, new HashClusterStrategyImpl());
    }


    public static ClusterStrategy queryClusterStrategy(String clusterStrategy) {
        ClusterStrategyEnum clusterStrategyEnum = ClusterStrategyEnum.queryByCode(clusterStrategy);
        if (clusterStrategyEnum == null) {
            //默认选择随机算法
            return new RandomClusterStrategyImpl();
        }

        return clusterStrategyMap.get(clusterStrategyEnum);
    }

}
