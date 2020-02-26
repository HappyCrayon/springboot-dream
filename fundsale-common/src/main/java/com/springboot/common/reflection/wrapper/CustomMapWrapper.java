package com.springboot.common.reflection.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

public class CustomMapWrapper extends MapWrapper {

    public CustomMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
//        if (useCamelCaseMapping
//                && ((name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')
//                || name.contains(StringPool.UNDERSCORE))) {
//            return StringUtils.underlineToCamel(name);
//        }
        return name.toLowerCase();
    }
}
