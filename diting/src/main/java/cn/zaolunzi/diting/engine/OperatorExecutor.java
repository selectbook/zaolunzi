package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.Operator;

/**
 * 操作器组件的执行者。 当执行器启动时，会创建一个新线程来重复调用算子组件的apply()函数。
 * 
 * @Author: SelectBook
 * @Date: 2022/5/29 8:36
 */
public class OperatorExecutor extends ComponentExecutor {
  private final Operator operator;

  public OperatorExecutor(Operator operator) {
    super(operator);
    this.operator = operator;
  }

  /**
   * 运行一次
   * @return 如果线程应该继续，则返回 true； 如果线程应该存在，则为 false。
   */
  @Override
  boolean runOnce() {
    Event event;
    try {
      // 读取输入
      event = incomingQueue.take();
    } catch (InterruptedException e) {
      return false;
    }

    // 应用操作
    operator.apply(event, eventCollector);

    // 发射出去
    try {
      for (Event output: eventCollector) {
        outgoingQueue.put(output);
      }
      eventCollector.clear();
    } catch (InterruptedException e) {
      return false;  // 关闭线程
    }
    return true;
  }
}
