package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.FieldsGrouping;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 15:49
 */
public class ZoneFieldsGrouping implements FieldsGrouping {
    @Override
    public Object getKey(Event event) {
        EmissionEvent e = (EmissionEvent) event;
        return e.zone;
    }
}
