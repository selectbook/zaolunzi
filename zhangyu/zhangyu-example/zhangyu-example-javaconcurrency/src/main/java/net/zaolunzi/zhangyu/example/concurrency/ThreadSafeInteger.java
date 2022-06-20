package net.zaolunzi.zhangyu.example.concurrency;

/**
 * @Author: SelectBook
 * @Date: 2022/6/20 23:43
 */
public class ThreadSafeInteger {
    private int value;
    public synchronized int get() {
        return value;
    }
    
    public synchronized void set(int value) {
        this.value = value;
    }
}
