package net.zaolunzi.zhangyu.example.nio;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @Author: SelectBook
 * @Date: 2022/6/19 1:18
 */
public class StreamInAndOut {
    public static void main(String[] args) {
        try(
                Stream<String> input =
                        Files.lines(Paths.get("StreamInAndOut.java"));
                PrintWriter output =
                        new PrintWriter("StreamInAndOut.txt")
        ) {
            input.map(String::toUpperCase)
                    .forEachOrdered(output::println);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
