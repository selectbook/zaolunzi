package cn.zaolunzi.feige.test;

/**
 * @Author: SelectBook
 * @Date: 2022/5/24 23:57
 */
public class HelloServiceImpl implements HelloService {


    @Override
    public String sayHello(String somebody) {
        return "hello " + somebody + "!";
    }


}
