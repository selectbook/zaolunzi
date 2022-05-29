package cn.zaolunzi.diting.engine;

/**
 * 这是所有进程（执行者）的基类。 当一个进程启动时，创建一个新线程来调用派生类的runOnce()函数。
 * 每个进程还有一个传入事件队列和一个传出事件队列
 * 
 * @Author: SelectBook
 * @Date: 2022/5/29 8:36
 */
public abstract class Process {
  private final Thread thread;

  public Process() {
    this.thread = new Thread() {
      @Override
      public void run() {
        while (runOnce()) {
          ;
        }
      }
    };
  }

  /**
   * 启动进程.
   */
  public void start() {
    thread.start();
  }

  /**
   * 运行一次进程。
   * @return 如果线程应该继续，则为 true； 如果线程应该存在，则为 false。
   */
  abstract boolean runOnce();
}
