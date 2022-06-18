package net.zaolunzi.zhangyu.example.nio;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: SelectBook
 * @Date: 2022/6/19 1:18
 */
public class ReadLineStream {
    public static void main(String[] args) throws Exception {
        Files.lines(Paths.get("PathInfo.java"))
                .skip(13)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
