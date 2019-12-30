package com.springboot.admin.response;

import com.springboot.common.exception.BusinessException;
import com.springboot.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice(basePackages = "com.springboot.admin.controller")
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

    //    /**
//     * 请求参数类型错误异常的捕获
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = {BindException.class})
//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public Result badRequest(BindException e) {
//        log.error("BAD_REQUEST {}", e.getMessage());
//        return build(HttpStatus.BAD_REQUEST);
//    }
//
//    /**
//     * 404错误异常的捕获
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = {NoHandlerFoundException.class})
//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public Result badRequestNotFound(BindException e) {
//        log.error("NOT_FOUND {}", e.getMessage());
//        return build(HttpStatus.NOT_FOUND);
//    }
//
//    /**
//     * mybatis未绑定异常
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(BindingException.class)
//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result mybatis(Exception e) {
//        log.error("occurs error when execute method ,message {}", e.getMessage());
//        return build(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    /**
//     * 数据库操作出现异常
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result databaseError(Exception e) {
//        log.error("database error", e);
//        return build(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
    @ExceptionHandler
    public Result businessException(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
        log.error("===调用[{}]", request.getRequestURL());
        log.error("GlobalExceptionHandler业务异常", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler
    public Result unknownException(HttpServletRequest request, HttpServletResponse response, final Exception e) {
        log.error("===调用[{}]", request.getRequestURL());
        log.error("GlobalExceptionHandler未知异常", e);
        return build(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
