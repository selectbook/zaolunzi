package net.zaolunzi.zhangyu.example.nio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: SelectBook
 * @Date: 2022/6/18 6:07
 */
public class PathInfo {
    static void show(String id, Object p) {
        System.out.println(id + ": " + p);
    }
    
    static void info(Path p) {
        show("toString", p);
        show("Exists", Files.exists(p));
        show("RegularFile", Files.isRegularFile(p));
        show("Directory", Files.isDirectory(p));
        show("Absolute", p.isAbsolute());
        show("FileName", p.getFileName());
        show("Parent", p.getParent());
        show("Root", p.getRoot());
        System.out.println("******************");
    }
    
    public static void main(String[] args) {
//        System.out.println(System.getProperty("os.name"));
//        info(Paths.get("C:", "path", "to", "nowhere", "NoFile.txt"));
        
        Path p = Paths.get("D:\\opensource\\zaolunzi\\zhangyu" +
                "\\zhangyu-example\\zhangyu-example-javaconcurrency" +
                "\\src\\main\\java\\net\\zaolunzi\\zhangyu\\example\\nio\\PathInfo.java");
        info(p);
//        // 1.获取当前文件所在的路径
//        System.out.println(new PathInfo().getClass().getResource("").getPath());
//        // 获取再 target 下 classpath 路径
//        System.out.println(new PathInfo().getClass().getResource("/").getPath());
//        // 4.也是获取 classpath 的绝对路径
//        System.out.println(new PathInfo().getClass().getClassLoader().getResource("").getPath());
        
        Path ap = p.toAbsolutePath();
        info(ap);
        info(ap.getParent());
        try {
            info(p.toRealPath());
        } catch(IOException e) {
            System.out.println(e);
        }
        URI u = p.toUri();
        System.out.println("URI: " + u);
        Path puri = Paths.get(u);
        System.out.println(Files.exists(puri));
        File f = ap.toFile(); // Don't be fooled
    }
}
