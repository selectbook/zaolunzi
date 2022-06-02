package cn.zaolunzi.diting.engine;

import cn.zaolunzi.diting.api.NameEventPair;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: SelectBook
 * @Date: 2022/6/2 15:07
 */
public class NamedEventQueue extends ArrayBlockingQueue<NameEventPair> {
    public final String streamName;
    
    public NamedEventQueue(int size, String streamName) {
        super(size);
        this.streamName = streamName;
    }
}
