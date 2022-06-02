//package cn.zaolunzi.diting.job;
//
//import cn.zaolunzi.diting.api.Event;
//import cn.zaolunzi.diting.api.EventCollector;
//import cn.zaolunzi.diting.api.EventWindow;
//import cn.zaolunzi.diting.api.GroupingStrategy;
//import cn.zaolunzi.diting.api.WindowOperator;
//
///**
// * @Author: SelectBook
// * @Date: 2022/6/1 15:24
// */
//public class TestWindowedAnalyzer extends WindowOperator {
//    private int instance;
//    
//    public TestWindowedAnalyzer(String name, int parallelism, GroupingStrategy grouping) {
//        super(name, parallelism, grouping);
//    }
//    
//    @Override
//    public void setupInstance(int instance) {
//        this.instance = instance;
//    }
//    
//    @Override
//    public void apply(EventWindow window, EventCollector eventCollector) {
//        Logger.log(String.format("%d transactions are received between %d and %d\n",
//                window.getEvents().size(), window.getStartTime(), window.getEndTime()));
//        for (Event event: window.getEvents()) {
//            Logger.log(String.format("Event: %s\n", event));
//        }
//    }
//}
