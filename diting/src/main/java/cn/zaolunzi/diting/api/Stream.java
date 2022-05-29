package cn.zaolunzi.diting.api;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Stream 类表示来自组件的数据流。
 * 具有正确类型的操作器可以应用于此流
 * 示例:
 *   Job job = new Job("my_job");
 *   job.addSource(mySource)
 *      .applyOperator(myOperator);
 */
public class Stream {
  // 要应用于此流的所有操作器的列表。
  private final Set<Operator> operatorSet = new HashSet<Operator>();

  /**
   * 将操作器应用于此流
   * @param operator 要连接到当前流的操作器
   * @return 该操作器的传出流。
   */
  public Stream applyOperator(Operator operator) {
    if (operatorSet.contains(operator)) {
      throw new RuntimeException("Operator " + operator.getName() + " is added to job twice");
    }

    operatorSet.add(operator);
    return operator.getOutgoingStream();
  }

  /**
   * 获取应用于此流的操作器集合。
   * @return
   */
  public Collection<Operator> getAppliedOperators() {
    return operatorSet;
  }
}
