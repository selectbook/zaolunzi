package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 2:00
 */
public class TransactionScoreEvent implements Event {
    public final TransactionEvent transaction;
    public final float score;
    
    public TransactionScoreEvent(TransactionEvent transaction, float score) {
        this.transaction = transaction;
        this.score = score;
    }
}
