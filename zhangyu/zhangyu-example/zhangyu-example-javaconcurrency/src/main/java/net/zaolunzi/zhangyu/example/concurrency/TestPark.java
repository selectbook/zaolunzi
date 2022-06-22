package net.zaolunzi.zhangyu.example.concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: SelectBook
 * @Date: 2022/6/22 0:58
 */
public class TestPark {
    
    public void testPark() {
        LockSupport.park(this);
    }
    
    public static void main(String[] args) {
        TestPark testPark = new TestPark();
        testPark.testPark();
    }
}
