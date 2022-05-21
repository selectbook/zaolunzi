package net.zaolunzi.framework;

import net.zaolunzi.framework.helper.BeanHelper;
import net.zaolunzi.framework.helper.ClassHelper;
import net.zaolunzi.framework.helper.ControllerHelper;
import net.zaolunzi.framework.helper.IocHelper;
import net.zaolunzi.framework.util.ClassUtil;

/**
 * @Author: SelectBook
 * @Date: 2022/5/20 1:37
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
