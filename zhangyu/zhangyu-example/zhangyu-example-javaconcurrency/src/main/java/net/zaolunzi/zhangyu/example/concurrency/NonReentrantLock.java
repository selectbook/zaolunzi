package net.zaolunzi.zhangyu.example.concurrency;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于AQS实现不可冲入的独占锁
 * 
 * @Author: SelectBook
 * @Date: 2022/6/21 23:15
 */
public class NonReentrantLock implements Lock, Serializable {
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否锁已经被持有
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        // 如果state为0，则尝试获取锁
        @Override
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        
        // 尝试释放锁，设置state为0
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    
        // 提供条件变量接口
        Condition newCondition() {
            return new ConditionObject();
        }
    }
    
    // 创建一个Sync来做具体的工作
    private final Sync sync = new Sync();
    
    @Override
    public void lock() {
        sync.acquire(1);
    }
    
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }
    
    @Override
    public void unlock() {
        sync.release(1);
    }
    
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
    
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
    
    @Override
    public void lockInterruptibly() throws InterruptedException {
       sync.acquireInterruptibly(1); 
    }
    
    @Override
    public boolean tryLock(final long time, final TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
}
