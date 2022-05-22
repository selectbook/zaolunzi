package net.zaolunzi.rest;

import net.zaolunzi.framework.helper.ConfigHelper;
import net.zaolunzi.framework.util.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: SelectBook
 * @Date: 2022/5/22 16:50
 */
public class RestConfig {
    public static boolean isLog() {
        return ConfigHelper.getBoolean(RestConstant.LOG);
    }
    
    public static boolean isJsonp() {
        return ConfigHelper.getBoolean(RestConstant.JSONP);
    }
    
    public static String getJsonpFunction() {
        return ConfigHelper.getString(RestConstant.JSONP_FUNCTION);
    }
    
    public static boolean isCors() {
        return ConfigHelper.getBoolean(RestConstant.CORS);
    }
    
    public static List<String> getCorsOriginList() {
        String corsOrigin = ConfigHelper.getString(RestConstant.CORS_ORIGIN);
        return Arrays.asList(StringUtil.splitString(corsOrigin, ","));
    }
}
