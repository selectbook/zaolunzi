package cn.zaolunzi.diting.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: SelectBook
 * @Date: 2022/5/31 3:06
 */
public class EventCollector {
    private static final String DEFAULT_CHANNEL = "default";
    
    private final Map<String, List<Event>> queues = new HashMap<String, List<Event>>();
    private final Set<String> registeredChannels = new HashSet<String>();
    
    public EventCollector() {
    }
    
    public void registerChannel(String channel) {
        registeredChannels.add(channel);
        queues.put(channel, new ArrayList<Event>());
    }
    
    public void add(Event event) {
        add(DEFAULT_CHANNEL, event);
    }
    
    public void add(String channel, Event event) {
        // 如果channel已注册，则将事件添加到相应的列表中。
        if (queues.containsKey(channel)) {
            queues.get(channel).add(event);
        }
    }
    
    public Set<String> getRegisteredChannels() {
        return registeredChannels;
    }
    
    public List<Event> getEventList(String channel) {
        return queues.get(channel);
    }
    
    public void clear() {
        for (List<Event> queue: queues.values()) {
            queue.clear();
        }
    }
}
