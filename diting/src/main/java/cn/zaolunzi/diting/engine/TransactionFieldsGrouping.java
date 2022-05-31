package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.FieldsGrouping;
import cn.zaolunzi.diting.job.TransactionScoreEvent;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 2:20
 */
public class TransactionFieldsGrouping implements FieldsGrouping {
    private static final long serialVersionUID = 2392789329942799704L;
    
    @Override
    public Object getKey(Event event) {
        TransactionScoreEvent e = (TransactionScoreEvent) event;
        return e.transaction.transactionId;
    }
}
