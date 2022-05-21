package net.zaolunzi.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * @Author: SelectBook
 * @Date: 2022/5/20 1:05
 */
public final class ArrayUtil {
    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }
    
    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
