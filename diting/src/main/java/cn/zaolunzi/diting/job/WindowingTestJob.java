//package cn.zaolunzi.diting.job;
//
//import cn.zaolunzi.diting.api.FixedTimeWindowingStrategy;
//import cn.zaolunzi.diting.api.Job;
//import cn.zaolunzi.diting.engine.JobStarter;
//
///**
// * @Author: SelectBook
// * @Date: 2022/6/1 15:25
// */
//public class WindowingTestJob {
//    private static final long FIXED_WINDOW_INTERVAL_MS = 5 * 1000;
//    private static final long FIXED_WINDOW_WATERMARK_MS = 2 * 1000;
//    
//    public static void main(String[] args) {
//        Job job = new Job("windowing_test_job");
//        
//        // Create a stream from a source.
//        job.addSource(new TransactionSource("transaction source", 1, 9990))
//                // Apply a windowing strategy and then apply a windowed operator.
//                .withWindowing(new FixedTimeWindowingStrategy(FIXED_WINDOW_INTERVAL_MS, FIXED_WINDOW_WATERMARK_MS))
//                .applyOperator(new TestWindowedAnalyzer("test windowed analyzer", 2, new UserAccountFieldsGrouping()));
//        
//        Logger.log("This is a streaming job that works with a windowed strategy and a windowed operator." +
//                "Input needs to be in this format: {amount},{merchandiseId}. For example: 42.00@3.");
//        JobStarter starter = new JobStarter(job);
//        starter.start();
//    }
//}
