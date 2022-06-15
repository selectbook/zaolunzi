package cn.zaolunzi.dijiang.referencejava.interfaces;

/**
 * 这里展示了创建 Operations 的不同方式：一个外部类 (Bing)，一个匿名类，一个
 * 方法引用和 lambda 表达式——毫无疑问用在这里是最好的解决方法。
 * 这个特性是一项改善，因为它允许把静态方法放在更合适的地方
 * 
 * @Author: SelectBook
 * @Date: 2022/6/14 1:39
 */

class Bing implements Operations {
    @Override
    public void execute() {
        Operations.show("Bing");
    }
}

class Crack implements Operations {
    @Override
    public void execute() {
        Operations.show("Crack");
    }
}

class Twist implements Operations {
    @Override
    public void execute() {
        Operations.show("Twist");
    }
}
public class Machine {
    public static void main(String[] args) {
        Operations.runOps(
        new Bing(), new Crack(), new Twist());
    }
    
}
