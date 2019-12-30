package com.springboot.common.response;


import com.springboot.common.enums.ResultCode;

public class ErrorResult {

	/**
	 * 异常状态码
	 */
	private String code;

	/**
	 * 用户看得见的异常，例如 用户名重复！！,
	 */
	private String message;

	/**
	 * 异常的名字
	 */
	private String exception;

	/**
	 * 异常堆栈信息
	 */
	//private String errors;

	/**
	 * 对异常提示语进行封装
	 */
	public static ErrorResult fail(ResultCode resultCode, Throwable e, String message) {
		ErrorResult result = ErrorResult.fail(resultCode, e);
		result.setMessage(message);
		return result;
	}

	/**
	 * 对异常枚举进行封装
	 */
	public static ErrorResult fail(ResultCode resultCode, Throwable e) {
		ErrorResult result = new ErrorResult();
		result.setMessage(resultCode.getMessage());
		result.setCode(resultCode.getCode());
		result.setException(e.getClass().getName());
		//result.setErrors(Throwables.getStackTraceAsString(e));
		return result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}
