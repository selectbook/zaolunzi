package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 15:41
 */
public class TemperatureEvent implements Event {
    public final int zone;
    public final float temperature;
    
    public TemperatureEvent(int zone, float temperature) {
        this.zone = zone;
        this.temperature = temperature;
    }
    
    @Override
    public String toString() {
        return String.format("[zone:%d; temperature:%f]", zone, temperature);
    }
}
