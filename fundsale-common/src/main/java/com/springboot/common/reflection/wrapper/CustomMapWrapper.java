package com.springboot.common.reflection.wrapper;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * 自定义java.util.Map类型字段名: 强制驼峰或小写
 */
public class CustomMapWrapper extends MapWrapper {

    public CustomMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping
                && ((name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')
                || name.contains(StringPool.UNDERSCORE))) {
            return StringUtils.underlineToCamel(name);
        }
        return name.toLowerCase();
    }
}
