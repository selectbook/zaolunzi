package net.zaolunzi.zhangyu.example.concurrency;

/**
 * @Author: SelectBook
 * @Date: 2022/6/19 20:50
 */
public class WaitThread extends Thread {
    private volatile boolean fire = false;
    
    @Override
    public void run() {
        try {
            synchronized (this) {
                while (! fire) {
                    wait();
                }
            }
            System.out.println("fired");
        } catch (InterruptedException e) {
            
        }
    }
    
    public synchronized void fire() {
        this.fire = true;
        notify();
    }
    
    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }
    
}
