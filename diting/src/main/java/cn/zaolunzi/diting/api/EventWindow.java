package cn.zaolunzi.diting.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 10:23
 */
public class EventWindow {
    private final List<Event> events = new ArrayList<>();
    private long startTime;
    private long endTime;
    
    public EventWindow(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    // Get the start timestamp of the window. The time is *inclusive*.
    public long getStartTime() {
        return startTime;
    }
    
    // Get the end timestamp of the window. The time is *exclusive*.
    public long getEndTime() {
        return endTime;
    }
    
    public void add(Event event) {
        events.add(event);
    }
    
    public List<Event> getEvents() {
        return events;
    }
}
