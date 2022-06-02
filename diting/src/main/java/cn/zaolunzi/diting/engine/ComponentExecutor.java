package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Component;

/**
 * source 和 operator 的执行者的基类
 * 
 * @Author: SelectBook
 * @Date: 2022/5/29 11:36
 */
public abstract class ComponentExecutor {
  protected Component component;
  protected InstanceExecutor [] instanceExecutors;
  protected NamedEventQueue [] incomingQueues;
  
  public ComponentExecutor(Component component) {
    this.component = component;
    int parallelism = component.getParallelism();
    this.instanceExecutors = new InstanceExecutor[parallelism];
  }
  
  /**
   * Start instance executors (real processes) of this component.
   */
  public abstract void start();
  
  /**
   * Get the instance executors of this component executor.
   */
  public InstanceExecutor [] getInstanceExecutors() {
    return instanceExecutors;
  }
  
  public Component getComponent() {
    return component;
  }
  
  public void registerChannel(String channel) {
    for (InstanceExecutor instance: instanceExecutors) {
      instance.registerChannel(channel);
    }
  }
  
  public void setIncomingQueues(NamedEventQueue [] queues) {
    incomingQueues = queues;
    for (int i = 0; i < queues.length; ++i) {
      instanceExecutors[i].setIncomingQueue(queues[i]);
    }
  }
  
  public NamedEventQueue[] getIncomingQueues() {
    return incomingQueues;
  }
  
  
  public void addOutgoingQueue(String channel, EventQueue queue) {
    for (InstanceExecutor instance: instanceExecutors) {
      instance.addOutgoingQueue(channel, queue);
    }
  }
}
