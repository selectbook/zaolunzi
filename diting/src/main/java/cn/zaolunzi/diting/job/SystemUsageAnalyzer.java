//package cn.zaolunzi.diting.job;
//
//
//import cn.zaolunzi.diting.api.Event;
//import cn.zaolunzi.diting.api.EventCollector;
//import cn.zaolunzi.diting.api.GroupingStrategy;
//import cn.zaolunzi.diting.api.Operator;
//
///**
// * @Author: SelectBook
// * @Date: 2022/6/1 5:38
// */
//public class SystemUsageAnalyzer extends Operator {
//    private int instance;
//    private int transactionCount = 0;
//    private int fraudTransactionCount = 0;
//    
//    public SystemUsageAnalyzer(String name, int parallelism, GroupingStrategy grouping) {
//        super(name, parallelism, grouping);
//    }
//    
//    @Override
//    public void setupInstance(int instance) {
//        this.instance = instance;
//    }
//    
//    @Override
//    public void apply(Event event, EventCollector collector) {
//        transactionCount++;
//        
//        // TODO: uncomment the code below to count fraud transactions.
//        // TransactionEvent e = ((TransactionEvent)event);
//        // String id = ((TransactionEvent)event).transactionId;
//        // Thread.sleep(20);
//        // boolean fraud = fraudStore.getItem(id);
//        // if (fraud) {
//        //        fraudTransactionCount++;
//        //}
//        collector.add(new UsageEvent( transactionCount, fraudTransactionCount));
//    }
//}
