package cn.zaolunzi.diting.api;

import java.util.ArrayList;

/**
 * Stream 类表示来自组件的数据流。
 * 具有正确类型的运算符可以应用于此流。
 * 例子：
 * Streams.merge(stream1, stream2, stream3).applyOperator(operator)
 * 或者
 * Streams.merge(stream1.selectChannel("second_channel"), stream2, stream3).applyOperator(operator)
 * 
 * @Author: SelectBook
 * @Date: 2022/5/31 3:10
 */
public class Streams {
    ArrayList<Stream> streams;
    
    private Streams(ArrayList<Stream> streams) {
        this.streams = streams;
    }
    
    public static Streams of(Stream ...streams) {
        ArrayList<Stream> list = new ArrayList<Stream>();
        for (Stream stream: streams) {
            list.add(stream);
        }
        return new Streams(list);
    }
    
    /**
     * Apply the operator to the streams.
     * @param operator
     * @return
     */
    public Stream applyOperator(Operator operator) {
        for (Stream stream: streams) {
            stream.applyOperator(operator);
        }
        return operator.getOutgoingStream();
    }
}
