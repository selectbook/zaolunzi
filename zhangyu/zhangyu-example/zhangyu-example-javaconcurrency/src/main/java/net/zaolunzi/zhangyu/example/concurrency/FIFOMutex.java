package net.zaolunzi.zhangyu.example.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: SelectBook
 * @Date: 2022/6/22 1:21
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedDeque<>();
    
    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);
        
        // 只有队首的线程可以获取锁（1）
        //  如果当前线程不是队首或者当前锁已经被其他线程获取，则调用park方法挂起自己
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            // (2) 如果park方法是因为中断而返回，则忽略中断，并且重置中断标志，做个标记
            // 然后再次判断当前线程是不是队首元素或者当前锁是否已经被其他线程获取，如果是则继续调用park方法挂起自己
            if (Thread.interrupted()) { 
                wasInterrupted = true;
            }
        }
        
        waiters.remove();
        // (3) 判断标记，如果标记为true则中断该线程
        if (wasInterrupted) { 
            current.interrupt(); 
        }
    }
    
    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
