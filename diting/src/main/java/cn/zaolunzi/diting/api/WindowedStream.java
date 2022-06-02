package cn.zaolunzi.diting.api;

import java.util.Map;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 10:38
 */
public class WindowedStream extends Stream {
    Stream baseStream;
    Map<String, WindowingStrategy> windowingMap;
    
    public WindowedStream(Stream baseStream, Map<String, WindowingStrategy> windowingMap) {
        this.baseStream = baseStream;
        this.windowingMap = windowingMap;
    }
    
    public Stream applyOperator(WindowOperator operator) {
        return baseStream.applyWindowOperator(windowingMap, operator);
    }
    
    // Joins are special type of operations. Side streams are needed for join operators
    // and materialize() function is applied to these side streams.
    public Stream join(Operator operator, Map<String, Stream> streams) {
        return null;
    }
    
    public Stream windowedJoin(Operator operator, Map<String, WindowedStream> streams) {
        return null;
    }
    
}
