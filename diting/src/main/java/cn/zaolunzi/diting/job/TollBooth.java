package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.EventCollector;
import cn.zaolunzi.diting.api.GroupingStrategy;
import cn.zaolunzi.diting.api.Operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 3:09
 */
public class TollBooth extends Operator {
    private static final long serialVersionUID = 6898974811832135305L;
    
    private Map<String, Integer> countMap = new HashMap<String, Integer>();
    private int instance = 0;
    
    public TollBooth(String name, int parallelism) {
        super(name, parallelism);
    }
    
    public TollBooth(String name, int parallelism, GroupingStrategy grouping) {
        super(name, parallelism, grouping);
    }
    
    @Override
    public void setupInstance(int instance) {
        this.instance = instance;
    }
    
    @Override
    public void apply(Event vehicleEvent, EventCollector eventCollector) {
        String vehicle = ((VehicleEvent)vehicleEvent).getType();
        Integer count = countMap.getOrDefault(vehicle, 0) + 1;
        countMap.put(vehicle, count);
        
        String countMap = printCountMap();
        Logger.log("toll booth (" + getName() + ") :: instance " + instance + " -->\n" + countMap);
    }
    
    private String printCountMap() {
        StringBuilder builder = new StringBuilder();
        List<String> vehicles = new ArrayList<>(countMap.keySet());
        Collections.sort(vehicles);
        
        for (String vehicle: vehicles) {
            builder.append("  " + vehicle + ": " + countMap.get(vehicle) +"\n");
        }
        return builder.toString();
    }
}
