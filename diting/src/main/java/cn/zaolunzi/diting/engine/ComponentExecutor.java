package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Component;
import cn.zaolunzi.diting.api.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * source 和 operator 的执行者的基类
 * 
 * @Author: SelectBook
 * @Date: 2022/5/29 11:36
 */
public abstract class ComponentExecutor extends Process {
  private final Component component;
  // 此列表用于接受来自用户逻辑的事件。
  protected final List<Event> eventCollector = new ArrayList<Event>();
  // 上游进程的数据队列
  protected EventQueue incomingQueue = null;
  // 下游进程的数据队列
  protected EventQueue outgoingQueue = null;

  public ComponentExecutor(Component component) {
    this.component = component;
  }

  public Component getComponent() {
    return component;
  }

  public void setIncomingQueue(EventQueue queue) {
    incomingQueue = queue;
  }

  public void setOutgoingQueue(EventQueue queue) {
    outgoingQueue = queue;
  }
}
