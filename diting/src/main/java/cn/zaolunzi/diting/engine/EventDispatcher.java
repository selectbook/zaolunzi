package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.GroupingStrategy;

/**
 * @Author: SelectBook
 * @Date: 2022/5/30 8:39
 */
public class EventDispatcher extends Process {
    private final OperatorExecutor downstreamExecutor;
    private EventQueue incomingQueue = null;
    private EventQueue [] outgoingQueues = null;
    
    public EventDispatcher(OperatorExecutor downstreamExecutor) {
        this.downstreamExecutor = downstreamExecutor;
    }
    
    @Override
    boolean runOnce() {
        try {
            Event event = incomingQueue.take();
            
            GroupingStrategy grouping = downstreamExecutor.getGroupingStrategy();
            int instance = grouping.getInstance(event, outgoingQueues.length);
            outgoingQueues[instance].put(event);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
    
    public void setIncomingQueue(EventQueue queue) {
        incomingQueue = queue;
    }
    
    public void setOutgoingQueues(EventQueue [] queues) {
        outgoingQueues = queues;
    }
}
