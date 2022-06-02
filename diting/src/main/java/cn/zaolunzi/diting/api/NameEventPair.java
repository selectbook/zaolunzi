package cn.zaolunzi.diting.api;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 15:08
 */
public class NameEventPair implements Event {
    private final String streamName;
    private final Event event;
    
    public NameEventPair(String streamName, Event event) {
        this.streamName = streamName;
        this.event = event;
    }
    
    public String getStreamName() {
        return streamName;
    }
    
    public Event getEvent() {
        return event;
    }
    
    @Override
    public String toString() {
        return String.format("[stream name:%s; event:%s]", streamName, event.toString());
    }
}
