package cn.zaolunzi.diting.api;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 10:38
 */
public class WindowedStream extends Stream {
    Stream baseStream;
    WindowingStrategy strategy;
    
    public WindowedStream(Stream baseStream, WindowingStrategy strategy) {
        this.baseStream = baseStream;
        this.strategy = strategy;
    }
    
    public Stream applyOperator(WindowOperator operator) {
        return baseStream.applyWindowOperator(strategy, operator);
    }
}
