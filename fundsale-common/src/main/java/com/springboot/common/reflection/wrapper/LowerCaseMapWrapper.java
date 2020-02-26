package com.springboot.common.reflection.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * 自定义java.util.Map类型字段名->强制小写
 */
public class LowerCaseMapWrapper extends MapWrapper {

    public LowerCaseMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        return name.toLowerCase();
    }
}
