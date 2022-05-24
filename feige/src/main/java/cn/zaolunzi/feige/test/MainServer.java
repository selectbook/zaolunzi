package cn.zaolunzi.feige.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: SelectBook
 * @Date: 2022/5/24 23:57
 */
public class MainServer {


    public static void main(String[] args) throws Exception {

        //发布服务
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("feige-server.xml");
        System.out.println(" 服务发布完成");
    }
}
