package cn.zaolunzi.diting.api;

import java.util.List;

/**
 * 基础 数据源 source类，以供用户自定义
 */
public abstract class Source extends Component {
  public Source(String name) {
    super(name);
  }

  /**
   * 将外部事件引入到系统中
   * 该方法是抽象方法，需要用户实现
   * @param eventCollector 对外发送的事件收集器
   */
  public abstract void getEvents(List<Event> eventCollector);
}
