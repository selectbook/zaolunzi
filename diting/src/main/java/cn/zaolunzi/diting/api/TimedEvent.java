package cn.zaolunzi.diting.api;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 14:52
 */
public interface TimedEvent extends Event {
    /**
     * Get event time (since midnight, January 1, 1970 UTC) in milliseconds.
     * @return
     */
    long getTime();
}
