package cn.zaolunzi.diting.api;

import java.io.Serializable;

/**
 * 使用 shuffle 分组，事件相对均匀地路由到下游实例。 这个实现是循环的，不是基于随机数，因为它更简单且具有确定性。
 */
public class ShuffleGrouping implements GroupingStrategy, Serializable {
  private static final long serialVersionUID = -1763295335424683087L;

  private int count = 0;

  public ShuffleGrouping() { }

  /**
   * 从事件和组件并行获取目标实例 ID。
   * @param event 要路由到组件的事件对象。
   * @param parallelism 组件的并行度
   * @return 此事件的整数键。
   */
  @Override
  public int getInstance(Event event, int parallelism) {
    if (count >= parallelism) {
      count = 0;
    }
    count++;
    return count - 1;
  }
}
