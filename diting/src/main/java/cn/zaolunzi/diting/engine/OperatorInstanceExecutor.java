package cn.zaolunzi.diting.engine;


import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.NameEventPair;
import cn.zaolunzi.diting.api.Operator;
import cn.zaolunzi.diting.api.WindowingOperator;

import java.util.concurrent.TimeUnit;

/**
 * 操作员组件的执行者。当执行器启动时，会创建一个新线程来重复调用算子组件的apply()函数。
 */
public class OperatorInstanceExecutor extends InstanceExecutor {
    private final int instanceId;
    private final Operator operator;
    
    public OperatorInstanceExecutor(int instanceId, Operator operator) {
        this.instanceId = instanceId;
        this.operator = operator;
        operator.setupInstance(instanceId);
    }
    
    /**
     * Run process once.
     * @return true if the thread should continue; false if the thread should exist.
     */
    @Override
    protected boolean runOnce() {
        NameEventPair pair;
        try {
            // Read input. Time out every one second to check if there is any event windows ready to be processed.
            pair = incomingQueue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
        
        // Apply operator
        if (operator instanceof WindowingOperator) {
            // WindowingOperator handles null events too.
            if (pair == null) {
                operator.apply(null, null, eventCollector);
            } else {
                operator.apply(pair.getStreamName(), pair.getEvent(), eventCollector);
            }
        } else if (pair != null) {
            // For regular operators.
            operator.apply(pair.getStreamName(), pair.getEvent(), eventCollector);
        }
        
        // Emit out
        try {
            for (String channel: eventCollector.getRegisteredChannels()) {
                for (Event output: eventCollector.getEventList(channel)) {
                    for (EventQueue queue: outgoingQueueMap.get(channel)) {
                        queue.put(output);
                    }
                }
            }
            eventCollector.clear();
        } catch (InterruptedException e) {
            return false;  // exit thread
        }
        return true;
    }
}
