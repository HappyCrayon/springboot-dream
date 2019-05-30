package com.springboot.admin.sqlparser;

import ognl.Ognl;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import java.util.*;

/**
 foreach节点属性如下
 collection 需要遍历的集合
 item 遍历集合后每个元素存放的变量
 index 遍历集合的索引数如0,1,2...
 separator 遍历后以指定分隔符拼接
 open 遍历后拼接开始的符号如 (
 close 遍历后拼接结束的符号如 )
 */
public class ForeachNode extends BaseNode {

    @Override
    public boolean parse(Map<String, Object> currParams, Map<String, Object> globalParams, Element ele, StringBuilder sb) throws Exception {

        String conditionStr = null;
        String collectionStr = ele.attributeValue("collection");
        String itemStr = ele.attributeValue("item");
        String index = ele.attributeValue("index");
        String separatorStr = ele.attributeValue("separator");
        String openStr = ele.attributeValue("open");
        String closeStr = ele.attributeValue("close");
        if(StringUtils.isEmpty(index)) {
            index = "index";
        }
        if(StringUtils.isEmpty(separatorStr)) {
            separatorStr = ",";
        }
        if(StringUtils.isNotEmpty(openStr)) {
            currParams.put(Attrs.WHILE_OPEN,openStr);
        }
        if(StringUtils.isNotEmpty(closeStr)) {
            currParams.put(Attrs.WHILE_CLOSE,closeStr);
        }
        if(StringUtils.isNotEmpty(collectionStr)) {
            currParams.put(Attrs.WHILE_LIST,collectionStr);
        }
        currParams.put(Attrs.WHILE_SEPARATOR,separatorStr);
        if(index != null) {
            /**
             * 如果局部变量中存在当前循环变量的值，就表示已经不是第一次进入循环标签了，移除掉开始标记
             * 并将局部变量值加1
             */
            if(currParams.get(index) != null) {
                currParams.remove(Attrs.WHILE_START);
                currParams.put(index+"_", (Integer)currParams.get(index+"_") + 1);
            } else { //第一次进入循环标签内
                currParams.put(Attrs.WHILE_START,true);
                currParams.put(index+"_", 0);
            }
            currParams.put(index, (Integer)currParams.get(index+"_"));
        }
        boolean condition = true;
        Map<String, Object> allParams = getAllParams(currParams,globalParams);
        Object collection = null;
        if(StringUtils.isNotEmpty(collectionStr)) {
            //得到待循环的集合
            collection = Ognl.getValue(collectionStr,allParams);
            //如果集合属性不为空，但是条件为null则默认加上一个边界条件
            if(StringUtils.isEmpty(conditionStr)) {
                //这里只是用集合演示一下，也可以再加上数组，只不过改成.length而已
                if(collection instanceof List) {
                    conditionStr = index+"_<"+collectionStr+".size()";
                } else if(collection instanceof Map){
                    Map map = (Map)collection;
                    Set set = map.entrySet();
                    List list = new ArrayList(set);
                    allParams.put("_list_", list);
                    conditionStr = index+"_<_list_"+".size()";
                }
            }
        }

        currParams.remove(Attrs.WHILE_END);
        if(StringUtils.isNotEmpty(conditionStr)) {
            //计算条件的值
            condition = (Boolean)Ognl.getValue(conditionStr,allParams);
            Map<String,Object> tempMap = new HashMap<>();
            tempMap.putAll(allParams);
            tempMap.put(index+"_",(Integer)currParams.get(index+"_") + 1);
            currParams.put(Attrs.WHILE_END,!(Boolean)Ognl.getValue(conditionStr,tempMap));
        }

        boolean flag = true;
        currParams.put(Attrs.WHILE_INDEX, index);
        currParams.put(Attrs.WHILE_FLAG, true);

        if(condition) {
            try {
                if(StringUtils.isNotEmpty(itemStr) && StringUtils.isNotEmpty(collectionStr)) {
                    Object value = null;
                    int idx = Integer.parseInt(currParams.get(index+"_").toString());
                    if(collection instanceof List) {
                        value = ((List)collection).get(idx);
                        currParams.put(itemStr, value);
                    } else if(collection instanceof Map){
                        Map map = (Map)collection;
                        Set<Map.Entry<String,Object>> set = map.entrySet();
                        List<Map.Entry<String,Object>> list = new ArrayList(set);
                        currParams.put(itemStr, list.get(idx).getValue());
                        currParams.put(index, list.get(idx).getKey());
                    }

                }
            } catch (Exception e) {
                throw new Exception("从集合或者映射取值"+currParams.get(index)+"错误"+e.getMessage());
            }

        } else {
            flag = false;
            destroyVars(currParams, index, itemStr);
        }

        return flag;
    }

    /**
     * 如果是第一次进入循环标签，则拼上open的内容
     */
    @Override
    public void pre(Map<String, Object> currParams, Map<String, Object> globalParams, Element ele, StringBuilder sb) throws Exception {
        super.pre(currParams, globalParams, ele, sb);
        boolean start = MapUtils.getBoolean(currParams,Attrs.WHILE_START,false);
        if(start) {
            String open = MapUtils.getString(currParams,Attrs.WHILE_OPEN);
            sb.append(open);
        }

    }

    /**
     * 如果是最后进入循环标签，则最后拼上close的内容
     */
    @Override
    public void after(Map<String, Object> currParams, Map<String, Object> globalParams, Element ele, StringBuilder sb) throws Exception {
        super.after(currParams, globalParams, ele, sb);
        boolean end = MapUtils.getBoolean(currParams,Attrs.WHILE_END,false);
        String separator = MapUtils.getString(currParams,Attrs.WHILE_SEPARATOR);
        if(!end && StringUtils.isNotEmpty(separator)) {
            sb.append(separator);
        }
        if(end)  {
            String close = MapUtils.getString(currParams,Attrs.WHILE_CLOSE);
            if(sb.toString().endsWith(separator)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(close);
        }
    }

    //释放临时变量
    private void destroyVars(Map<String, Object> currParams, String index,String varStr) {
        currParams.remove(Attrs.WHILE_INDEX);
        currParams.remove(Attrs.WHILE_FLAG);
        currParams.remove(Attrs.WHILE_SEPARATOR);
        currParams.remove(Attrs.WHILE_START);
        currParams.remove(Attrs.WHILE_END);
        currParams.remove(Attrs.WHILE_LIST);       currParams.remove(varStr);
        currParams.remove(index);
        currParams.remove(index+"_");
    }

}
