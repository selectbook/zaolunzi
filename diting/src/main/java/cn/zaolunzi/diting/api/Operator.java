package cn.zaolunzi.diting.api;

import java.io.Serializable;

/**
 * 所有用户自定义操作器（Operator） 的基础类.
 */
public abstract class Operator extends Component implements Serializable {
  private static final long serialVersionUID = -1972993710318354151L;
  
  // 传入数据的分组策略
  private final GroupingStrategy grouping;
  
  public Operator(String name, int parallelism) {
    super(name, parallelism);
    // 默认的分组策略
    this.grouping = new ShuffleGrouping();
  }
  
  public Operator(String name, int parallelism, GroupingStrategy grouping) {
    super(name, parallelism);
    this.grouping = grouping;
  }
  
  /**
   * 设置实例
   * @param instance 此源实例的实例 ID（从 0 开始的索引）
   */
  public abstract void setupInstance(int instance);
  
  /**
   * 将逻辑应用于传入事件并生成结果
   * 该方法是抽象的，需要用户自己实现
   * @param event 传入事件
   * @param eventCollector 传出事件收集器
   */
  public abstract void apply(Event event, EventCollector eventCollector);
  
  /**
   * 获取事件的分组键。
   * @return The grouping strategy of this operator
   */
  public GroupingStrategy getGroupingStrategy() {
    return grouping;
  }
}
