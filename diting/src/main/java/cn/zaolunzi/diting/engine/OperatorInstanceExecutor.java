package cn.zaolunzi.diting.engine;


import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.Operator;

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
     * 运行一次进程。
     *
     * @return 如果线程应该继续，则为 true； 如果线程应该存在，则为 false.
     */
    @Override
    protected boolean runOnce() {
        Event event;
        try {
            // 读取输入
            event = incomingQueue.take();
        } catch (InterruptedException e) {
            return false;
        }
        
        // 应用操作器
        operator.apply(event, eventCollector);
        
        // 发射出去
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
