package cn.zaolunzi.diting.api;

/**
 * 基础事件类
 * 用户可以继承该类以实现自定义事件类
 */
public abstract class Event {
  /**
   * 获取存储在事件中的数据
   * @return
   */
  public abstract Object getData();
}
