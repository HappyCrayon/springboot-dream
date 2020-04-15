package com.springboot.trade.typehandler;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.result.ResultMapException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.StringTypeHandler;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CustomStringTypeHandler extends StringTypeHandler {

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return getNullableResult(cs, columnIndex);
        } catch (Exception e) {
            throw new ResultMapException("Error attempting to get column #" + columnIndex+ " from callable statement.  Cause: " + e, e);
        }

    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return getNullableResult(rs, columnIndex);
        } catch (Exception e) {
            throw new ResultMapException("Error attempting to get column #" + columnIndex+ " from result set.  Cause: " + e, e);
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return getNullableResult(rs, columnName);
        } catch (Exception e) {
            throw new ResultMapException("Error attempting to get column '" + columnName + "' from result set.  Cause: " + e, e);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return StringUtils.defaultString(rs.getString(columnName), "");
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return StringUtils.defaultString(rs.getString(columnIndex), "");
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return StringUtils.defaultString(cs.getString(columnIndex), "");
    }
}
