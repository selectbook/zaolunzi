package cn.zaolunzi.feige.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Netty异步调用返回结果包装类
 *
 * @Author: SelectBook
 * @Date: 2022/5/24 8:36
 */
public class FeigeResponseWrapper {

    //存储返回结果的阻塞队列
    private BlockingQueue<FeigeResponse> responseQueue = new ArrayBlockingQueue<FeigeResponse>(1);
    //结果返回时间
    private long responseTime;

    /**
     * 计算该返回结果是否已经过期
     *
     * @return
     */
    public boolean isExpire() {
        FeigeResponse response = responseQueue.peek();
        if (response == null) {
            return false;
        }

        long timeout = response.getInvokeTimeout();
        if ((System.currentTimeMillis() - responseTime) > timeout) {
            return true;
        }
        return false;
    }

    public static FeigeResponseWrapper of() {
        return new FeigeResponseWrapper();
    }

    public BlockingQueue<FeigeResponse> getResponseQueue() {
        return responseQueue;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
