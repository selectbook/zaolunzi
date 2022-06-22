package net.zaolunzi.zhangyu.example.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: SelectBook
 * @Date: 2022/6/22 23:06
 */
public class JoinCountDownLatch2 {
    // 创建一个CountDownlatch实例
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 将线程池A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("Child threadOne over!");
            }
        });
    
        // 将线程池B添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("Child threadTwo over!");
            }
        });
        System.out.println("wait all child thread over!");
        // 等待子线程执行完毕，返回
        countDownLatch.await();
        System.out.println("all child thread over");
        executorService.shutdown();
    }
}
