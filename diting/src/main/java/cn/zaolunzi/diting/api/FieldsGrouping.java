package cn.zaolunzi.diting.api;

import java.io.Serializable;

public interface FieldsGrouping extends GroupingStrategy, Serializable {

  /**
   * 从事件中获取密钥。 子类可以覆盖这个函数
   * 以不同的方式计算密钥。 例如，从某些特定字段计算键。
   * @param event 要从中提取密钥的事件对象。
   * @return 要散列的数据。
   */
  Object getKey(Event event);

  /**
   * 从事件和组件并行获取目标实例 ID
   * @param event 要路由到组件的事件对象。
   * @param parallelism 组件的并行度。
   * @return 此事件的整数键。
   */
  @Override
  public default int getInstance(Event event, int parallelism) {
    return Math.abs(getKey(event).hashCode()) % parallelism;
  }
}
