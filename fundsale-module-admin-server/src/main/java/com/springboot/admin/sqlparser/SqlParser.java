package com.springboot.admin.sqlparser;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlParser {
    private Map<String,Object> currParams = new HashMap<String,Object>();
    /**
     delete from pl_pagewidget
     <if sqlparser="widgetcodes != null">
     where pagewidgetcode in
     <foreach collection="widgetcodes" item="item" index="index" open="(" separator="," close=")">
     <if sqlparser="index == 0">
     #{item}
     </if>
     <foreach collection="bs" item="b" index="index1" open="(" separator="," close=")">
     #{b}
     </foreach>
     </foreach>
     </if>
     <if sqlparser="a != null">
     and a = #{a}
     </if>
     */
    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("widgetcodes", Arrays.asList("1", "2"));
        map.put("bs", Arrays.asList("3", "4"));
        map.put("a", 1);
        SqlParser parser = new SqlParser();
        System.out
                .println(parser.parser("delete from pl_pagewidget\n"
                        + "\t<if sqlparser=\"widgetcodes != null\">\n"
                        + "\t\twhere pagewidgetcode in\n"
                        + "\t\t<foreach collection=\"widgetcodes\" item=\"item\" index=\"index\" open=\"(\" separator=\",\" close=\")\">\n"
                        + "\t\t  <if sqlparser=\"index == 0\">\n"
                        + "\t\t  #{item}\n"
                        + "\t\t  </if>\n"
                        + "\t\t  <foreach collection=\"bs\" item=\"b\" index=\"index1\" open=\"(\" separator=\",\" close=\")\">\n"
                        + "\t\t\t#{b}\n" + "\t\t  </foreach>\n"
                        + "\t\t</foreach>\n" + "\t</if>\n"
                        + "\t<if sqlparser=\"a != null\">\n"
                        + "\t\tand a = #{a}\n" + "\t</if>\n", map));
        System.out.println(parser.getParams());
    }
    public String parser(String xml, Map<String, Object> params)
            throws Exception {
        // xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+xml;
        //给输入的动态sql套一层xml标签
        xml = "<sql>"+xml+"</sql>";
        SAXReader reader = new SAXReader(false);
        Document document = reader.read(new StringReader(xml));
        Element element = document.getRootElement();
        Map<String, Object> currParams = new HashMap<String, Object>();
        StringBuilder sb = new StringBuilder();
        //开始解析
        parserElement(element, currParams, params, sb);
        return sb.toString();
    }
    /**
     * 使用递归解析动态sql
     * @param ele1 待解析的xml标签
     * @param currParams
     * @param globalParams
     * @param sb
     * @throws Exception
     */
    private void parserElement(Element ele1, Map<String, Object> currParams,
                               Map<String, Object> globalParams, StringBuilder sb)
            throws Exception {
        // 解析一个节点，比如解析到了一个if节点，假如test判断为true这里就返回true
        TempVal val = parserOneElement(currParams, globalParams, ele1, sb);
        //得到解析的这个节点的抽象节点对象
        BaseNode node = val.getNode();
        /**
         * 实际上这句之上的语句只是解析了xml的标签，并没有解析标签里的内容，这里
         * 表示要解析内容之前，如果有前置操作做一点前置操作
         */
        node.pre(currParams, globalParams, ele1, sb);
        //判断是否还需要解析节点里的内容，例如if节点test结果为true
        boolean flag = val.isContinue();
        // 得到该节点下的所有子节点的集合，包含普通文本
        List<Node> nodes = ele1.content();
        if (flag && !nodes.isEmpty()) {
            /**
             * 这里表示要进一步解析节点里的内容了，可以把节点类比成一个方法的外壳
             * 里面的内容类比成方法里的具体语句，开始解析节点的内容之前
             * 先创建本节点下的局部参数的容器，最方便当然是map
             */
            Map<String, Object> params = new HashMap<String, Object>();
            /**
             * 把外面传进来的局部参数，直接放入容器，由于本例中参数都是常用数据类型
             * 不会存在引用类型所以，这里相当于是一个copy，为了不影响外面传入的对象
             * 可以类比方法调用传入参数的情况
             */
            params.putAll(currParams);
            //循环所有子节点
            for (int i = 0; i < nodes.size();) {
                Node n = nodes.get(i);
                //如果节点是普通文本
                if (n instanceof Text) {
                    String text = ((Text) n).getStringValue();
                    if (StringUtils.isNotEmpty(text.trim())) {
                        //处理一下文本，如处理#{xx},直接替换${yy}为真实传入的值
                        sb.append(handText(text, params,globalParams));
                    }
                    i++;
                } else if (n instanceof Element) {
                    Element e1 = (Element) n;
                    // 递归解析xml子元素
                    parserElement(e1, params, globalParams, sb);
                    // 如果循环标志不为true则解析下一个标签
                    // 这里表示需要重复解析这个循环标签，则i不变,反之继续处理下一个元素
                    boolean while_flag = MapUtils.getBoolean(params,
                            Attrs.WHILE_FLAG, false);
                    if (!while_flag
                            || !NodeFactory.isWhile(n.getName())
                            || e1.attributeValue(Attrs.INDEX) == null
                            || !e1.attributeValue(Attrs.INDEX).equals(
                            params.get(Attrs.WHILE_INDEX))) {
                        i++;
                    }
                }
            }
            //节点处理之后做一些啥事
            node.after(currParams, globalParams, ele1, sb);
            // 回收当前作用域参数
            params.clear();
            params = null;
        }
    }
    /**
     * 处理文本替换掉#{item}这种参数
     * @param str
     * @param params
     * @return
     * @throws Exception
     */
    private String handText(String str, Map<String, Object> params,Map<String, Object> globalParams)
            throws Exception {
        //获取foreach这种标签中用于记录循环的变量
        String indexStr = MapUtils.getString(params, Attrs.WHILE_INDEX);
        Integer index = null;
        if(StringUtils.isNotEmpty(indexStr)) {
            index = MapUtils.getInteger(params, indexStr);
        }
        //匹配#{a}这种参数
        String reg1 = "(#\\{)(\\w+)(\\})";
        //匹配${a}这种参数
        String reg2 = "(\\$\\{)(\\w+)(\\})";
        Pattern p1 = Pattern.compile(reg1);
        Matcher m1 = p1.matcher(str);
        Pattern p2 = Pattern.compile(reg2);
        Matcher m2 = p2.matcher(str);
        String whileList = MapUtils.getString(params, Attrs.WHILE_LIST);
        Map<String,Object> allParams = getAllParams(params, globalParams);
        while(m1.find()) {
            String tmpKey = m1.group(2);
            String key = whileList == null?tmpKey:(whileList+"_"+tmpKey);
            key = index == null?key:(key+index);
            String reKey = "#{"+key+"}";
            //如果在foreach类似的循环里，可能需要将参数#{xx}替换成#{xx_0},#{xx_1}
            str = str.replace(m1.group(0), reKey);
            currParams.put(key, allParams.get(tmpKey));
        }
        while(m2.find()) {
            String tmpKey = m2.group(2);
            Object value = allParams.get(tmpKey);
            if(value != null) {
                str = str.replace(m2.group(0), getValue(value));
            }
        }
        return str;
    }
    private String getValue(Object value) {
        String result = "";
        if(value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = sdf.format((Date)value);
        } else {
            result = String.valueOf(value);
        }
        return result;
    }
    private Map<String, Object> getAllParams(Map<String, Object> currParams,
                                             Map<String, Object> globalParams) {
        Map<String,Object> allParams = new HashMap<String,Object>();
        allParams.putAll(globalParams);
        allParams.putAll(currParams);
        return allParams;
    }
    // 解析一个xml元素
    private TempVal parserOneElement(Map<String, Object> currParams,
                                     Map<String, Object> globalParams, Element ele, StringBuilder sb)
            throws Exception {
        //获取xml标签名
        String eleName = ele.getName();
        //解析一个节点后是否继续，如遇到if这种节点，就需要判断test里是否为空
        boolean isContinue = false;
        //声明一个抽象节点
        BaseNode node = null;
        if (StringUtils.isNotEmpty(eleName)) {
            //使用节点工厂根据节点名得到一个节点对象比如是if节点还是foreach节点
            node = NodeFactory.create(eleName);
            //解析一下这个节点，返回是否还需要解析节点里的内容
            isContinue = node.parse(currParams, globalParams, ele, sb);
        }
        return new TempVal(isContinue, ele, node);
    }
    public Map<String, Object> getParams() {
        return currParams;
    }
    /**
     * 封装一个xml元素被解析后的结果
     * @author rongdi
     */
    final static class TempVal {
        private boolean isContinue;
        private Element ele;
        private BaseNode node;
        public TempVal(boolean isContinue, Element ele, BaseNode node) {
            this.isContinue = isContinue;
            this.ele = ele;
            this.node = node;
        }
        public boolean isContinue() {
            return isContinue;
        }
        public void setContinue(boolean isContinue) {
            this.isContinue = isContinue;
        }
        public Element getEle() {
            return ele;
        }
        public void setEle(Element ele) {
            this.ele = ele;
        }
        public BaseNode getNode() {
            return node;
        }
        public void setNode(BaseNode node) {
            this.node = node;
        }
    }
}
