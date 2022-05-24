package cn.zaolunzi.feige.zookeeper;

import cn.zaolunzi.feige.model.InvokerService;
import cn.zaolunzi.feige.model.ProviderService;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * 服务治理接口
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public interface IRegisterCenter4Governance {

    /**
     * 获取服务提供者列表与服务消费者列表
     *
     * @param serviceName
     * @param appKey
     * @return
     */
    public Pair<List<ProviderService>, List<InvokerService>> queryProvidersAndInvokers(String serviceName, String appKey);


}
