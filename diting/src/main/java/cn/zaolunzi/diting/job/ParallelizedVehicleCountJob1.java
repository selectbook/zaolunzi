package cn.zaolunzi.diting.job;


import cn.zaolunzi.diting.api.Job;
import cn.zaolunzi.diting.api.Stream;
import cn.zaolunzi.diting.engine.JobStarter;

public class ParallelizedVehicleCountJob1 {

  public static void main(String[] args) {
    // 创建作业对象
    Job job = new Job("parallelized_vehicle_count");
    // 添加源对象并获取流
    Stream bridgeStream = job.addSource(new SensorReader("sensor-reader", 2, 9990));
    // 将运算符应用于流
    bridgeStream.applyOperator(new VehicleCounter("vehicle-counter", 1));

    System.out.println("This is a streaming job that counts vehicles from two input streams " +
            "in real time. Please enter vehicle types like 'car' and 'truck' in any of the " +
            "input terminals and look at the output");
    JobStarter starter = new JobStarter(job);
    starter.start();
  }
}
