package cn.zaolunzi.dijiang.referencejava.polymorphism;

/**
 * @Author: SelectBook
 * @Date: 2022/6/13 23:04
 */

class Instrument {
    public void play(Note n) {
        System.out.println("Instrument.play()");
    }
}


public class Wind extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Wind.play()" + n);
    }
}

