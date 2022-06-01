package cn.zaolunzi.diting.api;

import java.util.List;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 10:55
 */
public final class WindowingOperator extends Operator {
    private final WindowingStrategy strategy;
    private final WindowOperator operator;
    
    public WindowingOperator(String name, int parallelism, WindowingStrategy strategy, WindowOperator operator, GroupingStrategy grouping) {
        super(name, parallelism, grouping);
        this.strategy = strategy;
        this.operator = operator;
    }
    
    /**
     * Set up instance.
     * @param instance The instance id (an index starting from 0) of this source instance.
     */
    @Override
    public void setupInstance(int instance) {
        operator.setupInstance(instance);
    }
    
    /**
     * Apply logic to the incoming event. This event based version is already implemented by WindowOperator.
     * Users should implement the windowed version of it.
     * @param event The incoming event
     * @param eventCollector The outgoing event collector
     */
    @Override
    public final void apply(Event event, EventCollector eventCollector) {
        long processingTime = System.currentTimeMillis();
        if (event != null) {
            strategy.add(event, processingTime);
        }
        
        List<EventWindow> windows = strategy.getEventWindows(processingTime);
        for (EventWindow window: windows) {
            operator.apply(window, eventCollector);
        }
    }
}
