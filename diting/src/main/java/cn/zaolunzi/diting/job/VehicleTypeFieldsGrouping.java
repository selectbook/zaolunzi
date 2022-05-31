package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.FieldsGrouping;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 3:14
 */
public class VehicleTypeFieldsGrouping implements FieldsGrouping {
    private static final long serialVersionUID = -1432651705385964857L;
    
    @Override
    public Object getKey(Event event) {
        VehicleEvent e = (VehicleEvent) event;
        return e.getType();
    }
}
