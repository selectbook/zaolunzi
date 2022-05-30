package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Source;
import org.apache.commons.lang3.SerializationUtils;

/**
 * 源组件的执行者。 当执行器启动时，创建一个新线程重复调用源组件的getEvents()函数。
 * 
 * @Author: SelectBook
 * @Date: 2022/5/29 8:36
 */
public class SourceExecutor extends ComponentExecutor {
  
  public SourceExecutor(Source source) {
    super(source);
    
    for (int i = 0; i < source.getParallelism(); ++i) {
      Source cloned = SerializationUtils.clone(source);
      instanceExecutors[i] = new SourceInstanceExecutor(i, cloned);
    }
  }
  
  @Override
  public void start() {
    if (instanceExecutors != null) {
      for (InstanceExecutor executor : instanceExecutors) {
        executor.start();
      }
    }
  }
  
  @Override
  public void setIncomingQueues(EventQueue [] queues) {
    throw new RuntimeException("No incoming queue is allowed for source executor");
  }
}
