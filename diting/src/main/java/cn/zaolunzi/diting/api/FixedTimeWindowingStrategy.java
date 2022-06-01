package cn.zaolunzi.diting.api;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 15:27
 */
public class FixedTimeWindowingStrategy extends SlidingTimeWindowingStrategy {
    public FixedTimeWindowingStrategy(long intervalMillis, long watermarkMillis) {
        super(intervalMillis, intervalMillis, watermarkMillis);
    }
}
