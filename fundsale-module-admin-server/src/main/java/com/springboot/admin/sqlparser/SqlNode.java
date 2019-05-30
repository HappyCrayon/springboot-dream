package com.springboot.admin.sqlparser;

import org.dom4j.Element;

import java.util.Map;

public class SqlNode extends BaseNode{

    @Override
    public boolean parse(Map<String, Object> currParams, Map<String, Object> globalParams, Element ele,StringBuilder sb) throws Exception {
        return true;
    }

}
