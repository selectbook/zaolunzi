package cn.zaolunzi.diting.api;

import java.io.Serializable;

/**
 * 所有组件的基础类, 包括 Source 和 Operator.
 */
public class Component implements Serializable {
  private static final long serialVersionUID = 528858207266782093L;
  private String name;
  private int parallelism;
  // 该 stream 对象用来连接下游 operators
  private Stream outgoingStream = new Stream();

  public Component(String name, int parallelism) {
    this.name = name;
    this.parallelism = parallelism;
  }

  /**
   * 获取component（组件）的名字
   * @return
   */
  public String getName() {
    return name;
  }
  
  public int getParallelism() {
    return parallelism;
  }
  
  /**
   * 获取该组件对外发送的事件流。该事件流用来连接下游的组件
   * @return
   */
  public Stream getOutgoingStream() {
    return outgoingStream;
  }
}
