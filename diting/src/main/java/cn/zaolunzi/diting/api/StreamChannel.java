package cn.zaolunzi.diting.api;

/**
 * @Author: SelectBook
 * @Date: 2022/5/31 3:11
 */
public class StreamChannel extends Stream{
    private static final long serialVersionUID = -155228644146537952L;
    Stream baseStream;
    String channel;
    
    public StreamChannel(Stream baseStream, String channel) {
        this.baseStream = baseStream;
        this.channel = channel;
    }
    
    @Override
    public Stream applyOperator(Operator operator) {
        return baseStream.applyOperator(channel, operator, null);
    }
    
    @Override
    public Stream applyOperator(Operator operator, String streamName) {
        return baseStream.applyOperator(channel, operator, streamName);
    }
}
