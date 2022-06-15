package cn.zaolunzi.dijiang.referencejava.polymorphism;

/**
 * 
 * 在 main() 中你看到了 tune() 方法传入了一个 Wind 引用，而没有做类型转换。
 * 这样做是允许的—— Instrument 的接口一定存在于 Wind 中，因此 Wind 继承了
 * Instrument。从 Wind 向上转型为 Instrument 可能 “缩小” 接口，但不会比 Instrument 的全部接口更少
 * 
 * @Author: SelectBook
 * @Date: 2022/6/13 23:11
 */
public class Music {
    public static void tune(Instrument i) {
        // ..
        i.play(Note.MIDDLE_C);
    }
    
    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);
    }
}
