package cn.zaolunzi.dijiang.referencejava.interfaces;

/**
 * @Author: SelectBook
 * @Date: 2022/6/14 1:33
 */
public interface Operations {
    void execute();
    static void runOps(Operations... ops) {
        for (Operations op: ops) {
            op.execute();
        }
    }
    static void show(String msg) {
        System.out.println(msg);
    }
}
