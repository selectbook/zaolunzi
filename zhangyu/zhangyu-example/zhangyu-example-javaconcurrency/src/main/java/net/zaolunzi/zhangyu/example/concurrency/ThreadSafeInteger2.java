package net.zaolunzi.zhangyu.example.concurrency;

/**
 * @Author: SelectBook
 * @Date: 2022/6/20 23:45
 */
public class ThreadSafeInteger2 {
    private volatile int value;
    
    public int get() {
        return value;
    }
    
    public void set(int value) {
        this.value = value;
    }
}
