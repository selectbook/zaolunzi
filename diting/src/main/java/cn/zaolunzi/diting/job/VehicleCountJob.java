package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Job;
import cn.zaolunzi.diting.api.Stream;
import cn.zaolunzi.diting.engine.JobStarter;
public class VehicleCountJob {

  public static void main(String[] args) {
    // 创建作业对象。
    Job job = new Job("vehicle_count");
    // 添加源对象并获取流。
    Stream bridgeStream = job.addSource(new SensorReader("sensor-reader", 9990));
    // 将运算符应用于流。
    bridgeStream.applyOperator(new VehicleCounter("vehicle-counter"));

    System.out.println("This is a streaming job that counts vehicles in real time. " +
        "Please enter vehicle types like 'car' and 'truck' in the input terminal " +
        "and look at the output");
    JobStarter starter = new JobStarter(job);
    // 启动作业
    starter.start();
  }
}
