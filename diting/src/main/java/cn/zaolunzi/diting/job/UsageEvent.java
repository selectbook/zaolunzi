package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 5:42
 */
public class UsageEvent implements Event {
    public final int transactionCount;
    public final int fraudTransactionCount;
    
    public UsageEvent(int transactionCount, int fraudTransactionCount) {
        this.transactionCount = transactionCount;
        this.fraudTransactionCount = fraudTransactionCount;
    }
    
    @Override
    public String toString() {
        return String.format("[transaction count: %d; fraud transaction count: %d]",
                transactionCount, fraudTransactionCount);
    }
}
