package cn.zaolunzi.diting.api;

/**
 * 所有组件的基础类, 包括 Source 和 Operator.
 */
public class Component {
  private String name;
  // 该 stream 对象用来连接下游 operators
  private Stream outgoingStream = new Stream();

  public Component(String name) {
    this.name = name;
  }

  /**
   * 获取component（组件）的名字
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * 获取该组件对外发送的事件流。该事件流用来连接下游的组件
   * @return
   */
  public Stream getOutgoingStream() {
    return outgoingStream;
  }
}
