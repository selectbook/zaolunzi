package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.FieldsGrouping;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 2:13
 */
public class UserAccountFieldsGrouping implements FieldsGrouping {
    private static final long serialVersionUID = -921436160232556027L;
    
    @Override
    public Object getKey(Event event) {
        TransactionEvent e = (TransactionEvent) event;
        return e.userAccount;
    }
}
