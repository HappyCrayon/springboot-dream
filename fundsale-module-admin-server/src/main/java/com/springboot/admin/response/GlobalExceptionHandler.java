package com.springboot.admin.response;

import com.springboot.common.enums.ResultCode;
import com.springboot.common.exceptions.BusinessException;
import com.springboot.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice//(basePackages = "com.springboot.admin.controller")
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

//    @ExceptionHandler
//    public Result unknownException(HttpServletRequest request, HttpServletResponse response, final Exception e) {
//        log.error("系统异常->URL:[{}]", request.getRequestURL(), e);
//        return build(HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    /**
     * 处理运行时异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result handleThrowable(Throwable e, HttpServletRequest request) {
        //TODO 运行是异常，可以在这里记录，用于发异常邮件通知
        log.error("系统异常->URL:[{}]", request.getRequestURL(), e);
        return build(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result businessException(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
        Result error = Result.error(e.getCode(), e.getMessage());
        log.error("业务异常->URL:[{}][{}]", request.getRequestURL(), error);
        return error;
    }

    /**
     * validator 统一异常封装
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String msgs = this.handle(e.getBindingResult().getFieldErrors());
        log.error("参数校验异常->URL:[{}][{}]", request.getRequestURI(), msgs);
        return Result.error(ResultCode.PARAM_IS_INVALID);
    }

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError obj : fieldErrors) {
            sb.append(obj.getField());
            sb.append("=[");
            sb.append(obj.getDefaultMessage());
            sb.append("]  ");
        }
        return sb.toString();
    }

//    /**
//     * Assert的异常统一封装
//     */
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
//        ErrorResult error = ErrorResult.builder().status(4000)
//                .message(e.getMessage())
//                .exceptions(e.getClass().getName())
//                .build();
//        log.warn("URL:{} ,业务校验异常:{}", request.getRequestURI(), e);
//        return error;
//    }
}
