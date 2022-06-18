package net.zaolunzi.zhangyu.example.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: SelectBook
 * @Date: 2022/6/18 19:24
 */
public class PathAnalysis {
    
    static void say(String id, Object result) {
        System.out.print(id + ": "); System.out.println(result);
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("os.name")); 
        Path p = Paths.get("D:\\opensource\\zaolunzi\\zhangyu\\" +
                "zhangyu-example\\zhangyu-example-javaconcurrency\\" +
                "src\\main\\java\\net\\zaolunzi\\zhangyu\\example\\nio\\PathAnalysis.java").toAbsolutePath();
        say("Exists", Files.exists(p)); say("Directory", Files.isDirectory(p));
        say("Executable", Files.isExecutable(p)); say("Readable", Files.isReadable(p));
        say("RegularFile", Files.isRegularFile(p)); say("Writable", Files.isWritable(p));
        say("notExists", Files.notExists(p)); say("Hidden", Files.isHidden(p)); say("size", Files.size(p));
        say("FileStore", Files.getFileStore(p)); say("LastModified: ", Files.getLastModifiedTime(p));
        say("Owner", Files.getOwner(p)); say("ContentType", Files.probeContentType(p));
        say("SymbolicLink", Files.isSymbolicLink(p)); 
        if (Files.isSymbolicLink(p)) {
            say("SymbolicLink", Files.readSymbolicLink(p));
        }
        if (FileSystems.getDefault().supportedFileAttributeViews().contains("posix")) {
            say("PosixFilePermissions", Files.getPosixFilePermissions(p));
        }
    }
}
