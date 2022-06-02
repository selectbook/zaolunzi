package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.GroupingStrategy;
import cn.zaolunzi.diting.api.NameEventPair;

/**
 * 将数据路由到并行化组件的不同实例
 * 
 * @Author: SelectBook
 * @Date: 2022/5/30 8:39
 */
public class EventDispatcher extends Process {
    private final OperatorExecutor downstreamExecutor;
    private EventQueue incomingQueue = null;
    private NamedEventQueue [] outgoingQueues = null;
    
    public EventDispatcher(OperatorExecutor downstreamExecutor) {
        this.downstreamExecutor = downstreamExecutor;
    }
    
    @Override
    boolean runOnce() {
        try {
            Event event = incomingQueue.take();
            
            final GroupingStrategy grouping = downstreamExecutor.getGroupingStrategy(incomingQueue.streamName);
            int instance = grouping.getInstance(event, outgoingQueues.length);
            if (instance == GroupingStrategy.ALL_INSTANCES) {
                for (NamedEventQueue queue: outgoingQueues) {
                    queue.put(new NameEventPair(incomingQueue.streamName, event));
                }
            } else {
                outgoingQueues[instance].put(new NameEventPair(incomingQueue.streamName, event));
            }
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
    
    public void setIncomingQueue(EventQueue queue) {
        incomingQueue = queue;
    }
    
    public void setOutgoingQueues(NamedEventQueue [] queues) {
        outgoingQueues = queues;
    }
}
