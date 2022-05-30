package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Event;
import java.util.ArrayList;
import java.util.List;

/**
 * 源组件的执行者。 当执行器启动时，会创建一个新线程来重复调用源组件的getEvents()函数。
 *
 * @Author: SelectBook
 * @Date: 2022/5/30 00:36
 */
public abstract class InstanceExecutor extends Process {
  // 此列表用于接受来自用户逻辑的事件。
  protected final List<Event> eventCollector = new ArrayList<Event>();
  // 上游进程的数据队列
  protected EventQueue incomingQueue = null;
  // 下游进程的数据队列
  protected EventQueue outgoingQueue = null;

  public InstanceExecutor() { }

  public void setIncomingQueue(EventQueue queue) {
    incomingQueue = queue;
  }

  public void setOutgoingQueue(EventQueue queue) {
    outgoingQueue = queue;
  }
}
