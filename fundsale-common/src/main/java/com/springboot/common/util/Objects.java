package com.springboot.common.util;

import org.springframework.aop.support.AopUtils;
import org.springframework.lang.Nullable;

public class Objects {

    /**
     * 获取类的真实Class类型
     * @param instance
     * @return
     */
    public static Class getTargetClass(@Nullable Object instance) {
        if (AopUtils.isAopProxy(instance) || AopUtils.isCglibProxy(instance)) {
            return AopUtils.getTargetClass(instance);
        }
        return instance.getClass();
    }
}
