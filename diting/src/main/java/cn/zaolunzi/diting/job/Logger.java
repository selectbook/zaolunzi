package cn.zaolunzi.diting.job;

/**
 * @Author: SelectBook
 * @Date: 2022/6/1 1:45
 */
public class Logger {
    public static synchronized void log(String message) {
        System.out.print(message);
    }
}
