package cn.zaolunzi.diting.job;


import cn.zaolunzi.diting.api.Event;
import cn.zaolunzi.diting.api.EventCollector;
import cn.zaolunzi.diting.api.Operator;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 15:36
 */
public class EmissionResolver extends Operator {
    private int instance;
    private final EmissionTable emissionTable = new EmissionTable();
    
    public EmissionResolver(String name, int parallelism) {
        super(name, parallelism);
    }
    
    @Override
    public void setupInstance(int instance) {
        this.instance = instance;
    }
    
    @Override
    public void apply(String streamName, Event event, EventCollector eventCollector) {
        VehicleTemperatureEvent vehicleTemp = (VehicleTemperatureEvent) event;
        double emission = emissionTable.getEmission(
                vehicleTemp.make, vehicleTemp.model, vehicleTemp.year, vehicleTemp.temperature, 4);
        EmissionEvent emissionEvent = new EmissionEvent(vehicleTemp.time, vehicleTemp.zone, emission);
        eventCollector.add(emissionEvent);
        Logger.log(
                String.format(
                        "EmissionResolver :: instance %d --> resolved emission %s\n",
                        instance, emissionEvent.toString()
                )
        );
    }
}
