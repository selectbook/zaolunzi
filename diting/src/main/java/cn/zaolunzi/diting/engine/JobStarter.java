package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.Component;
import cn.zaolunzi.diting.api.Job;
import cn.zaolunzi.diting.api.Operator;
import cn.zaolunzi.diting.api.Source;
import cn.zaolunzi.diting.api.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @Author: SelectBook
 * @Date: 2022/5/29 8:36
 */
public class JobStarter {
  private final static int QUEUE_SIZE = 64;

  // 要启动的作业
  private final Job job;
  // 执行器集合
  private final List<ComponentExecutor> executorList = new ArrayList<ComponentExecutor>();

  // 组件执行器之间的连接
  private final List<Connection> connectionList = new ArrayList<Connection>();

  public JobStarter(Job job) {
    this.job = job;
  }

  public void start() {
    // 为所有组件设置执行器。
    setupComponentExecutors();

    // 现在所有组件都已创建。 建立连接以将组件连接在一起。
    setupConnections();

    // 启动所有进程
    startProcesses();

    // 启动web服务器
    new WebServer(job.getName(), connectionList).start();
  }

  /**
   * 创建所有 source 和 operator 执行程序。
   */
  private void setupComponentExecutors() {
    // 从job中的source开始，遍历组件创建executor
    for (Source source: job.getSources()) {
      SourceExecutor executor = new SourceExecutor(source);
      executorList.add(executor);
      // 对于每个 source，遍历与其相关的操作。
      traverseComponent(source, executor);
    }
  }

  /**
   * 在所有组件执行器之间建立连接（中间队列）。
   */
  private void setupConnections() {
    for (Connection connection: connectionList) {
      connectExecutors(connection);
    }
  }

  /**
   * 启动作业的所有进程。
   */
  private void startProcesses() {
    Collections.reverse(executorList);
    for (ComponentExecutor executor: executorList) {
      executor.start();
    }
  }

  private void connectExecutors(Connection connection) {
    // 它是一个新连接的算子执行器。 请注意，在此版本中，没有共享的“from”组件和“to”组件。 该作业看起来像一个单链表。
    EventQueue intermediateQueue = new EventQueue(QUEUE_SIZE);
    connection.from.setOutgoingQueue(intermediateQueue);
    connection.to.setIncomingQueue(intermediateQueue);
  }

  private void traverseComponent(Component component, ComponentExecutor executor) {
    Stream stream = component.getOutgoingStream();

    for (Operator operator: stream.getAppliedOperators()) {
      OperatorExecutor operatorExecutor = new OperatorExecutor(operator);
      executorList.add(operatorExecutor);
      connectionList.add(new Connection(executor, operatorExecutor));
      // 为下游操作符设置执行器
      traverseComponent(operator, operatorExecutor);
    }
  }
}
