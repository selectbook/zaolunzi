package net.zaolunzi.zhangyu.example.nio;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: SelectBook
 * @Date: 2022/6/19 1:15
 */
public class ListOfLines {
    public static void main(String[] args) throws Exception {
        Files.readAllLines(
                Paths.get("../streams/Cheese.dat"))
                .stream()
                .filter(line -> !line.startsWith("//"))
                .map(line ->
                        line.substring(0, line.length()/2))
                .forEach(System.out::println);
    }
    
}
