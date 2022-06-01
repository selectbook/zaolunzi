package cn.zaolunzi.diting.api;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 10:39
 */
public abstract class WindowOperator extends Operator {
    public WindowOperator(String name, int parallelism) {
        super(name, parallelism);
    }
    
    public WindowOperator(String name, int parallelism, GroupingStrategy grouping) {
        super(name, parallelism, grouping);
    }
    
    /**
     * Apply logic to the incoming event. This event based version is already implemented by WindowedOperator.
     * Users should implement the windowed version of it.
     * @param event The incoming event
     * @param eventCollector The outgoing event collector
     */
    @Override
    public final void apply(Event event, EventCollector eventCollector) {
        throw new RuntimeException("apply(Event, EventCollector) is not supported by WindowedOperator.");
    }
    
    public abstract void apply(EventWindow window, EventCollector eventCollector);
}
