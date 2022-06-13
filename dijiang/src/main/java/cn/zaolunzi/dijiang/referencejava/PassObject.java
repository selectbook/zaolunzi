package cn.zaolunzi.dijiang.referencejava;

/**
 * @Author: SelectBook
 * @Date: 2022/6/13 1:17
 */

class Letter {
    char c;
}

public class PassObject {
    static void f(Letter y) {
        y.c = 'z';
        
    }
    public static void main(String[] args) {
        Letter x = new Letter();
        x.c = 'a';
        System.out.println("1: x.c: " + x.c);
        f(x);
        System.out.println("2: x.c: " + x.c);
    }
}
