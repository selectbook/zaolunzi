package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.TimedEvent;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 15:31
 */
public class EmissionEvent implements TimedEvent {
    public final long time;
    public final int zone;
    public final double emission;
    
    public EmissionEvent(long time, int zone, double emission) {
        this.time = time;
        this.zone = zone;
        this.emission = emission;
    }
    
    @Override
    public long getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        return String.format("[time:%d; zone:%d, emission:%f]", time, zone, emission);
    }
}
