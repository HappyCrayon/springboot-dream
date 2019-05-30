package com.springboot.admin.sqlparser;

import ognl.Ognl;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import java.util.Map;

/**
 * if节点
 * @author rongdi
 */
public class IfNode extends BaseNode{

    @Override
    public boolean parse(Map<String, Object> currParams, Map<String, Object> globalParams, Element ele,StringBuilder sb) throws Exception {
        //得到if节点的test属性
        String testStr = ele.attributeValue("sqlparser");
        boolean test = false;
        try {
            if(StringUtils.isNotEmpty(testStr)) {
                //合并全局变量和局部变量
                Map<String, Object> allParams = getAllParams(currParams,globalParams);
                //使用ognl判断true或者false
                test = (Boolean) Ognl.getValue(testStr,allParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("判断操作参数"+testStr+"不合法");
        }

        if(ele.content() != null && ele.content().size()==0) {
            test = true;
        }

        return test;
    }

}
