package cn.zaolunzi.diting.api;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户使用 Job 类来设置他们的作业并运行
 * 示例:
 *   Job job = new Job("my_job");
 *   job.addSource(mySource)
 *      .applyOperator(myOperator);
 */
public class Job {
  private final String name;
  private final Set<Source> sourceSet = new HashSet<Source>();

  public Job(String jobName) {
    this.name = jobName;
  }

  /**
   * 将 source 添加到作业中。 返回一个流，该流将用于连接到其他操作器。
   * @param source 要添加到作业中的source对象
   * @return 可用于连接其他操作器的流
   */
  public Stream addSource(Source source) {
    if (sourceSet.contains(source)) {
      throw new RuntimeException("Source " + source.getName() + " is added to job twice");
    }

    sourceSet.add(source);
    return source.getOutgoingStream();
  }

  /**
   * 获取该作业的名字
   */
  public String getName() {
    return name;
  }

  /**
   *获取此作业中的 source 集合。 JobRunner 使用此函数来遍历 graph。
   * @return The list of sources in this job
   */
  public Collection<Source> getSources() {
    return sourceSet;
  }
}
