package net.zaolunzi.soap;

import net.zaolunzi.framework.helper.ConfigHelper;

/**
 * 从配置文件中获取相关属性
 *
 * @Author: SelectBook
 * @Date: 2022/5/22 16:48
 */
public class SoapConfig {

    public static boolean isLog() {
        return ConfigHelper.getBoolean(SoapConstant.LOG);
    }
}
