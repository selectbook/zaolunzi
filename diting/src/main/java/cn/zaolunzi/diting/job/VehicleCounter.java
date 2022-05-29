package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.Operator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class VehicleCounter extends Operator {
  private final Map<String, Integer> countMap = new HashMap<String, Integer>();

  public VehicleCounter(String name) {  super(name);  }

  @Override
  public void apply(Event vehicleEvent, List<Event> eventCollector) {
    String vehicle = ((VehicleEvent)vehicleEvent).getData();
    // 从map中检索计数。
    Integer count = countMap.getOrDefault(vehicle, 0);
    // 增加计数
    count += 1;
    // 将计数保存回地图
    countMap.put(vehicle, count);
    // 打印当前计数
    System.out.println("VehicleCounter --> ");
    printCountMap();
  }

  private void printCountMap() {
    List<String> vehicles = new ArrayList<>(countMap.keySet());
    Collections.sort(vehicles);

    for (String vehicle: vehicles) {
      System.out.println("  " + vehicle + ": " + countMap.get(vehicle));
    }
  }
}
