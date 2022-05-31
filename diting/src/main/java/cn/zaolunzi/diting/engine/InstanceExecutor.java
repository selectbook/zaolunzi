package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.EventCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 源组件的执行者。 当执行器启动时，会创建一个新线程来重复调用源组件的getEvents()函数。
 *
 * @Author: SelectBook
 * @Date: 2022/5/30 00:36
 */
public abstract class InstanceExecutor extends Process {
  // 此列表用于接受来自用户逻辑的事件。
  protected final EventCollector eventCollector = new EventCollector();
  // 上游进程的数据队列
  protected EventQueue incomingQueue = null;
  // 下游进程的数据队列
  protected Map<String, List<EventQueue>> outgoingQueueMap = new HashMap<String, List<EventQueue>>();
  
  public InstanceExecutor() { }
  
  public void registerChannel(String channel) {
    eventCollector.registerChannel(channel);
  }
  
  public void setIncomingQueue(EventQueue queue) {
    incomingQueue = queue;
  }
  
  public void addOutgoingQueue(String channel, EventQueue queue) {
    if (outgoingQueueMap.containsKey(channel)) {
      outgoingQueueMap.get(channel).add(queue);
    } else {
      List<EventQueue> queueList = new ArrayList<EventQueue>();
      queueList.add(queue);
      outgoingQueueMap.put(channel, queueList);
    }
  }
}
