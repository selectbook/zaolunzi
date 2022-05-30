package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;

/**
 *
 * @Author: SelectBook
 * @Date: 2022/5/30 00:36
 */
public class VehicleEvent extends Event {
  // 获取事件中存储的车辆数据
  private final String type;

  // vehicle以字符串形式存储的构造函数
  public VehicleEvent(String type) {
    this.type = type;
  }

  // 车辆的内部字符串
  @Override
  public String getData() {
    return type;
  }
}
