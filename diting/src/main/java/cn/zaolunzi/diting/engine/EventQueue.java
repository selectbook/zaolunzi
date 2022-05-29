package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Event;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 这是进程之间的中间事件队列的类。
 * @Author: SelectBook
 * @Date: 2022/5/29 11:36
 */
public class EventQueue extends ArrayBlockingQueue<Event> {
  private static final long serialVersionUID = 3673430816396878407L;

  public EventQueue(int size) {
      super(size);
  }
}
