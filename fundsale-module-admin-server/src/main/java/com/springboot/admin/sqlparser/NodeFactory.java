package com.springboot.admin.sqlparser;



import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  节点工厂
 */
public class NodeFactory {

    private static Map<String,BaseNode> nodeMap = new ConcurrentHashMap<String,BaseNode>();

    private final static List<String> whileList = Arrays.asList("foreach");

    static {
        nodeMap.put("if", new IfNode());
        nodeMap.put("sql", new SqlNode());
        nodeMap.put("foreach", new ForeachNode());
    }

    public static boolean isWhile(String elementName) {
        return whileList.contains(elementName);
    }

    public static void addNode(String nodeName,BaseNode node) {

        nodeMap.put(nodeName, node);

    }

    public static BaseNode create(String nodeName) {

        return nodeMap.get(nodeName);

    }

}
