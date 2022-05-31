package cn.zaolunzi.diting.engine;

/**
 * 用于组件之间连接的工具数据类。
 * @Author: SelectBook
 * @Date: 2022/5/29 11:36
 */
class Connection {
  public final ComponentExecutor from;
  public final OperatorExecutor to;
  public final String channel;

  public Connection(ComponentExecutor from, OperatorExecutor to, String channel) {
    this.from = from;
    this.to = to;
    this.channel = channel;
  }
}
