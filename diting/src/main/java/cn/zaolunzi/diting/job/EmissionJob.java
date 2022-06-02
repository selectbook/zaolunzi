package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.AllGrouping;
import cn.zaolunzi.diting.api.FixedTimeWindowingStrategy;
import cn.zaolunzi.diting.api.Job;
import cn.zaolunzi.diting.api.NamedStreams;
import cn.zaolunzi.diting.api.ShuffleGrouping;
import cn.zaolunzi.diting.api.Stream;
import cn.zaolunzi.diting.engine.JobStarter;

import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 15:52
 */
public class EmissionJob {
    private static final long FIXED_WINDOW_INTERVAL_MS = 5 * 1000;
    private static final long FIXED_WINDOW_WATERMARK_MS = 2 * 1000;
    
    public static void main(String[] args) {
        Job job = new Job("emission job");
        
        // Create a stream from a source.
        Stream vehicles = job.addSource(new VehicleEventSource("vehicle source", 1, 9990));
        Stream temperatures = job.addSource(new TemperatureEventSource("temperature source", 1, 9991));
        
        NamedStreams.of(Map.of(vehicles, "vehicle", temperatures, "temperature"))
                .join(new EventJoiner("join operator", 2,
                        Map.of("vehicle", new ShuffleGrouping(), "temperature", new AllGrouping())))
                .applyOperator(new EmissionResolver("emission resolver", 1))
                .withWindowing(new FixedTimeWindowingStrategy(FIXED_WINDOW_INTERVAL_MS, FIXED_WINDOW_WATERMARK_MS))
                .applyOperator(new WindowedAggregator("windowed aggregator", 2, new ZoneFieldsGrouping()));
        
        Logger.log("This is a streaming job that joins vehicle and temperature events calculate the CO2" +
                "emission in real time. Input needs to be in this format:\n" +
                "vehicle (terminal 1): {make},{model},{year},{zone}. For example: XXX,AA,2020,3.\n" +
                "temperature (terminal 2): {temperature},{zone}. For example: 90,3\n");
        JobStarter starter = new JobStarter(job);
        starter.start();
    }
}
