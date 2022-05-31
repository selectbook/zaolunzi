package cn.zaolunzi.diting.engine;


import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.Source;

/**
 * 源组件的执行者。 当执行器启动时，会创建一个新线程来重复调用源组件的getEvents()函数。
 */
public class SourceInstanceExecutor extends InstanceExecutor {
  private final int instanceId;
  private final Source source;

  public SourceInstanceExecutor(int instanceId, Source source) {
    this.instanceId = instanceId;
    this.source = source;
    source.setupInstance(instanceId);
  }

  /**
   * 运行一次进程。
   * @return 如果线程应该继续，则为 true； 如果线程应该存在，则为 false。
   */
  @Override
  protected boolean runOnce() {
    // Generate events
    try {
      source.getEvents(eventCollector);
    } catch (Exception e) {
      return false;  // exit thread
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
