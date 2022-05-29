package cn.zaolunzi.diting.api;

import java.util.List;

/**
 * 所有用户自定义操作器（Operator） 的基础类.
 */
public abstract class Operator extends Component {
  public Operator(String name) {
    super(name);
  }

  /**
   * 将逻辑应用于传入事件并生成结果
   * 该方法是抽象的，需要用户自己实现
   * @param event 传入事件
   * @param eventCollector 传出事件收集器
   */
  public abstract void apply(Event event, List<Event> eventCollector);
}
