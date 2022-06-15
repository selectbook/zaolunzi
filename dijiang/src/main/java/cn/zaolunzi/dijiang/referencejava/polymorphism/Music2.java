package cn.zaolunzi.dijiang.referencejava.polymorphism;

/**
 * 这样行得通，但是有一个主要缺点：必须为添加的每个新 Instrument 类编写特定
 * 的方法。这意味着开始时就需要更多的编程，而且以后如果添加类似 tune() 的新方法
 * 这样行得通，但是有一个主要缺点：必须为添加的每个新 Instrument 类编写特定
 * 的方法。这意味着开始时就需要更多的编程，而且以后如果添加类似 tune() 的新方法
 *
 * @Author: SelectBook
 * @Date: 2022/6/13 23:22
 */

class Stringed extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }
}

class Brass extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Brass.play() " + n);
    }
}

public class Music2 {
    public static void tune(Wind i) {
        i.play(Note.MIDDLE_C);
    }
    
    public static void tune(Stringed i) {
        i.play(Note.MIDDLE_C);
    }
    
    public static void tune(Brass i) {
        i.play(Note.MIDDLE_C);
    }
    
    public static void main(String[] args) {
        Wind flute = new Wind(); 
        Stringed violin = new Stringed(); 
        Brass frenchHorn = new Brass();
        tune(flute); // No upcasting
        tune(violin); tune(frenchHorn);
    }
}
