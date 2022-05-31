package cn.zaolunzi.diting.job;

import cn.zaolunzi.diting.api.Job;
import cn.zaolunzi.diting.api.Stream;
import cn.zaolunzi.diting.api.Streams;
import cn.zaolunzi.diting.engine.JobStarter;
import cn.zaolunzi.diting.engine.TransactionFieldsGrouping;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 2:11
 */
public class FraudDetectionJob {
    public static void main(String[] args) {
        Job job = new Job("fraud_detection_job");
        
        // Create a stream from a source.
        Stream transactionOut = job.addSource(new TransactionSource("transaction source", 1, 9990));
        // One stream can have multiple channels. Different operator can be hooked up
        // to different channels to receive different events. When no channel is selected,
        // the default channel will be used.
        Stream evalResults1 = transactionOut.applyOperator(new AvgTicketAnalyzer("avg ticket analyzer",
                2, new UserAccountFieldsGrouping()));
        Stream evalResults2 = transactionOut.applyOperator(new WindowedProximityAnalyzer("windowed proximity analyzer",
                2, new UserAccountFieldsGrouping()));
        Stream evalResults3 = transactionOut.applyOperator(new WindowedTransactionCountAnalyzer("windowed transaction count analyzer",
                2, new UserAccountFieldsGrouping()));
        
        ScoreStorage store = new ScoreStorage();
        Streams.of(evalResults1, evalResults2, evalResults3)
                .applyOperator(new ScoreAggregator("score aggregator", 2, new TransactionFieldsGrouping(), store));
        
        Logger.log("This is a streaming job that detect suspicious transactions." +
                "Input needs to be in this format: {amount},{merchandiseId}. For example: 42.00@3." +
                "Merchandises N and N + 1 are 1 seconds walking distance away from each other.");
        JobStarter starter = new JobStarter(job);
        starter.start();
    }
}
