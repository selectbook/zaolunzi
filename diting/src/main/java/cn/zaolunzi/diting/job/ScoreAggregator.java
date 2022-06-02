//package cn.zaolunzi.diting.job;
//
//import cn.zaolunzi.diting.api.Event;
//import cn.zaolunzi.diting.api.EventCollector;
//import cn.zaolunzi.diting.api.GroupingStrategy;
//import cn.zaolunzi.diting.api.Operator;
//
///**
// * @Author: SelectBook
// * @Date: 2022/6/1 2:09
// */
//public class ScoreAggregator extends Operator {
//    private static final long serialVersionUID = -224012821029619376L;
//    private int instance;
//    private final ScoreStorage store;
//    
//    public ScoreAggregator(String name, int parallelism, GroupingStrategy grouping, ScoreStorage store) {
//        super(name, parallelism, grouping);
//        this.store = store;
//    }
//    
//    @Override
//    public void setupInstance(int instance) {
//        this.instance = instance;
//    }
//    
//    @Override
//    public void apply(Event score, EventCollector eventCollector) {
//        TransactionScoreEvent e = ((TransactionScoreEvent)score);
//        float oldScore = store.get(e.transaction.transactionId, 0);
//        store.put(e.transaction.transactionId, oldScore + e.score);
//    }
//}
