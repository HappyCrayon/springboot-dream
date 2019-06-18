package com.springboot.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * SQL辅助工具类
 */
@Component
public class SQLUtil {

    private static SqlSessionTemplate sqlSessionTemplate;

    private static final String BASE_MAPPER = "com.springboot.common.mapper.ComnBaseMapper";

    private static final String SQL_CONTENT = "sql_content";

    @Autowired(required = true)
    private void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        SQLUtil.sqlSessionTemplate = sqlSessionTemplate;
    }

    public static List selectList(String selectSql, JSONObject params) {
        Map sParams = (params != null ? params.getInnerMap() : Maps.newHashMap());
        sParams.put(SQL_CONTENT, selectSql);
        return sqlSessionTemplate.selectList(parseStatementId("comnSelect"), sParams);
    }

    public static int insert(String insertSql, JSONObject params) {
        Map sParams = (params != null ? params.getInnerMap() : Maps.newHashMap());
        sParams.put(SQL_CONTENT, insertSql);
        return sqlSessionTemplate.insert(parseStatementId("comnInsert"), sParams);
    }

    public static int update(String updateSql, JSONObject params) {
        Map sParams = (params != null ? params.getInnerMap() : Maps.newHashMap());
        sParams.put(SQL_CONTENT, updateSql);
        return sqlSessionTemplate.update(parseStatementId("comnUpdate"), sParams);
    }

    public static int delete(String deleteSql, JSONObject params) {
        Map sParams = (params != null ? params.getInnerMap() : Maps.newHashMap());
        sParams.put(SQL_CONTENT, deleteSql);
        return sqlSessionTemplate.update(parseStatementId("comnDelete"), sParams);
    }

    private static String parseStatementId(String methodName) {
        return BASE_MAPPER + "." + methodName;
    }
}
