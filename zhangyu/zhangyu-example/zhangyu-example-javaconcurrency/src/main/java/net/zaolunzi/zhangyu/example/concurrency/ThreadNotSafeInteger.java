package net.zaolunzi.zhangyu.example.concurrency;

/**
 * @Author: SelectBook
 * @Date: 2022/6/20 23:42
 */
public class ThreadNotSafeInteger {
    private int value;
    public int get() {
        return value;
    }
    
    public void set(int value) {
        this.value = value;
    }
}
