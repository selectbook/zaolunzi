package cn.zaolunzi.diting.job;


import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

class SensorReader extends Source {
    private final BufferedReader reader;
    
    public SensorReader(String name, int port) {
        super(name);
        
        reader = setupSocketReader(port);
    }
    
    /**
     * 流系统的生命周期钩子，用于执行用户定义的逻辑
     * @param eventCollector 对外发送的事件收集器
     */
    @Override
    public void getEvents(List<Event> eventCollector) {
        try {
            // 从输入中读取一种车辆类型。
            String vehicle = reader.readLine(); 
            if (vehicle == null) {
                // 当用户关闭服务器时退出。
                System.exit(0);
            }
            // 将字符串发射到收集器中。
            eventCollector.add(new VehicleEvent(vehicle));
            System.out.println("");  // 记录新事件之前的空行
            System.out.println("SensorReader --> " + vehicle);
        } catch (IOException e) {
            System.out.println("Failed to read input: " + e);
        }
    }
    
    private BufferedReader setupSocketReader(int port) {
        try {
            Socket socket = new Socket("47.106.94.179", port); InputStream input = socket.getInputStream();
            return new BufferedReader(new InputStreamReader(input));
        } catch (UnknownHostException e) {
            e.printStackTrace(); System.exit(0);
        } catch (IOException e) {
            e.printStackTrace(); System.exit(0);
        } return null;
    }
}
