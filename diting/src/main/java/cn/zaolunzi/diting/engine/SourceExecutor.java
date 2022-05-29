package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.Source;

/**
 * 源组件的执行者。 当执行器启动时，创建一个新线程重复调用源组件的getEvents()函数。
 * 
 * @Author: SelectBook
 * @Date: 2022/5/29 8:36
 */
public class SourceExecutor extends ComponentExecutor {
  private final Source source;

  public SourceExecutor(Source source) {
    super(source);
    this.source = source;
  }

  /**
   * 运行一次
   * @return 如果线程应该继续，则返回 true； 如果线程应该存在，则为 false。
   */
  @Override
  boolean runOnce() {
    // 生成事件
    try {
      source.getEvents(eventCollector);
    } catch (Exception e) {
      return false;  // 退出线程
    }

    // 发射出去
    try {
      for (Event event: eventCollector) {
        outgoingQueue.put(event);
      }
      eventCollector.clear();
    } catch (InterruptedException e) {
      return false;  // 退出线程
    }
    return true;
  }

  @Override
  public void setIncomingQueue(EventQueue queue) {
    throw new RuntimeException("No incoming queue is allowed for source executor");
  }
}
