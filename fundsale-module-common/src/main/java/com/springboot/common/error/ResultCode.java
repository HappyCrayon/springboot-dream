package com.springboot.common.error;

/**
 * 统一返回格式接口
 */
public interface ResultCode {
//    /**
//     * 操作是否成功,true为成功，false操作失败
//     *
//     * @return
//     */
//    boolean success();

    /**
     * 提示码
     *
     * @return
     */
    String getCode();

    /**
     * 提示信息
     *
     * @return
     */
    String getMessage();

}
