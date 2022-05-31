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
  protected InstanceExecutor[] instanceExecutors;
  
  public ComponentExecutor(Component component) {
    this.component = component;
    int parallelism = component.getParallelism();
    this.instanceExecutors = new InstanceExecutor[parallelism];
  }
  
  /**
   * 启动该组件的实例执行器（真实进程）
   */
  public abstract void start();
  
  /**
   * 获取该组件执行器的实例执行器。
   * @return
   */
  public InstanceExecutor[] getInstanceExecutors() {
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
  
  public void setIncomingQueues(EventQueue [] queues) {
    for (int i = 0; i < queues.length; ++i) {
      instanceExecutors[i].setIncomingQueue(queues[i]);
    }
  }
  
  public void addOutgoingQueue(String channel, EventQueue queue) {
    for (InstanceExecutor instance: instanceExecutors) {
      instance.addOutgoingQueue(channel, queue);
    }
  }
}
