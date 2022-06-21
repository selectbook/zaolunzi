package net.zaolunzi.zhangyu.example.concurrency;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 * @Author: SelectBook
 * @Date: 2022/6/21 23:51
 */
public class ProduceCustomer {
    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();
    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int queueSize = 10;
    
    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();
                try {
                    // 如果队列满了，则等待
                    while(queue.size() == queueSize) {
                        notEmpty.await();
                    }
                    // 添加元素到队列
                    queue.add("ele");
                    notFull.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        });
        
        
    }
    
}
