package cn.zaolunzi.diting.api;

import java.io.Serializable;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 0:48
 */
public class AllGrouping implements GroupingStrategy, Serializable {
    public AllGrouping() { }
    
    /**
     * Get target instance id from an event and component parallelism.
     * @param event The event object to route to the component.
     * @param The parallelism of the component.
     * @return The integer key of this event.
     */
    @Override
    public int getInstance(Event event, int parallelism) {
        return ALL_INSTANCES;
    }
}
