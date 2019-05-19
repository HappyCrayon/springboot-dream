package com.springboot.common.util;

import com.alibaba.fastjson.JSONObject;

public final class ResponseUtil {

    private static final String RTN_CODE = "rtn_code";

    private static final String RTN_DESC = "rtn_desc";

    public static String buildSuccessRes() {
        JSONObject response = new JSONObject();
        response.put(RTN_CODE, "00000");
        response.put(RTN_DESC,"处理成功");
        return response.toJSONString();
    }

    public static String buildFailureRes(String rtnCode, String rtnDesc) {
        JSONObject response = new JSONObject();
        response.put(RTN_CODE, rtnCode);
        response.put(RTN_DESC, rtnDesc);
        return response.toJSONString();
    }

}
