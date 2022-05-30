package cn.zaolunzi.diting.api;

import java.io.Serializable;
import java.util.List;

/**
 * 基础 数据源 source类，以供用户自定义
 */
public abstract class Source extends Component implements Serializable {
    private static final long serialVersionUID = -4760189183877654639L;
    
    public Source(String name, int parallelism) {
        super(name, parallelism);
    }
    
    /**
     * Set up instance.
     * @param instance The instance id (an index starting from 0) of this source instance.
     */
    public abstract void setupInstance(int instance);
    
    /**
     * 将外部事件引入到系统中
     * 该方法是抽象方法，需要用户实现
     *
     * @param eventCollector 对外发送的事件收集器
     */
    public abstract void getEvents(List<Event> eventCollector);
}
