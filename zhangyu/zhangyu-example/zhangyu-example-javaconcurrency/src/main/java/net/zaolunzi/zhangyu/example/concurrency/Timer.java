package net.zaolunzi.zhangyu.example.concurrency;

import static java.util.concurrent.TimeUnit.*;

/**
 * @Author: SelectBook
 * @Date: 2022/6/18 4:37
 */
public class Timer {
    private long start = System.nanoTime();
    public long duration() {
        return NANOSECONDS.toMillis(
                System.nanoTime() - start);
    }
    public static long duration(Runnable test) {
        Timer timer = new Timer();
        test.run();
        return timer.duration();
    }
}
