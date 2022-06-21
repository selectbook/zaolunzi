package net.zaolunzi.zhangyu.example.concurrency;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: SelectBook
 * @Date: 2022/6/21 0:53
 */
public class RandomTest {
    public static void main(String[] args) {
        // 创建一个默认种子的随机数生成数
//        Random random = new Random();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // 输出10个0-5(包含0, 不包含5)之间的随机数
        for (int i = 0; i < 10; ++i) {
            System.out.println(random.nextInt(5));
        }
    }
}
