package com.springboot.admin.exception;

import com.springboot.common.exception.BusinessException;
import com.springboot.common.result.Result;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private Result build(HttpStatus httpStatus) {
        return Result.error(String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase());
    }

//    @ExceptionHandler
//    public ResultBean unknownAccount(UnknownAccountException e) {
//        log.error("账号不存在", e);
//        return ResultBean.error(1, "账号不存在");
//    }

    /**
     * 请求参数类型错误异常的捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result badRequest(BindException e) {
        log.error("BAD_REQUEST {}", e.getMessage());
        return build(HttpStatus.BAD_REQUEST);
    }

    /**
     * 404错误异常的捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Result badRequestNotFound(BindException e) {
        log.error("NOT_FOUND {}", e.getMessage());
        return build(HttpStatus.NOT_FOUND);
    }

    /**
     * mybatis未绑定异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindingException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result mybatis(Exception e) {
        log.error("occurs error when execute method ,message {}", e.getMessage());
        return build(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 数据库操作出现异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result databaseError(Exception e) {
        log.error("database error", e);
        return build(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler
    public Result businessException(BusinessException e) {
        log.error("GlobalExceptionHandler业务异常", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler
    public Result unknownException(Exception e) {
        log.error("GlobalExceptionHandler未知异常", e);
        // 发送邮件通知技术人员.
        return Result.error("-99", "系统出现错误, 请联系网站管理员!");
    }
}
