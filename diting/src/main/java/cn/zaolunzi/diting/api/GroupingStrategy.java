package cn.zaolunzi.diting.api;

/**
 *
 * @Author: SelectBook
 * @Date: 2022/5/30 00:36
 */
public interface GroupingStrategy {
  /**
   * 从事件和组件并行中获取目标实例 ID。
   * 请注意，在此实现中，仅选择了一个实例。
   * 如果需要，这可以很容易地扩展。
   * @param event 要路由到组件的事件对象。
   * @param parallelism 组件的并行度
   * @return 此事件的整数键
   */
  int getInstance(Event event, int parallelism);
}
