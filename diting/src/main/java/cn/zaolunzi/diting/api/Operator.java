package cn.zaolunzi.diting.api;

import java.util.Map;

/**
 * 所有用户自定义操作器（Operator） 的基础类.
 */
public abstract class Operator extends Component {
  private final Map<String, GroupingStrategy> groupingMap;
  
  // 传入数据的分组策略
  
  public Operator(String name, int parallelism) {
    this(name, parallelism, new ShuffleGrouping());
  }
  
  public Operator(String name, int parallelism, GroupingStrategy grouping) {
    this(name, parallelism, Map.of("default", grouping));
  }
  
  public Operator(String name, int parallelism, Map<String, GroupingStrategy> groupingMap) {
    super(name, parallelism);
    this.groupingMap = groupingMap;
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
  public abstract void apply(String streamName, Event event, EventCollector eventCollector);
  
  /**
   * Get the grouping strategy for a specific stream.
   * @return The grouping strategy of this operator
   */
  public GroupingStrategy getGroupingStrategy(String streamName) {
    return groupingMap.get(streamName);
  }
  
  /**
   * Get the grouping strategy map. This function is used by WindowingOperator only.
   * @return The grouping strategy of this operator
   */
  Map<String, GroupingStrategy> getGroupingStrategyMap() {
    return groupingMap;
  }
}
