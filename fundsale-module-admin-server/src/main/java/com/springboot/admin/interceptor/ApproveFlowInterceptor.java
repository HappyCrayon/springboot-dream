package com.springboot.admin.interceptor;

import com.springboot.admin.filter.WrappedHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 审批流拦截器
 */
public class ApproveFlowInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ApproveFlowInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request instanceof  WrappedHttpServletRequest) {
			logger.info("====preHandle");
			System.out.println("我是拦截器，拦截的uri:" + request.getRequestURI());

			WrappedHttpServletRequest requestWrapper = (WrappedHttpServletRequest) request;
			String params = requestWrapper.getRequestParams();
			logger.info("拦截的请求参数:params:[{}]", params);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		logger.info("====postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("====afterCompletion");
	}

}
