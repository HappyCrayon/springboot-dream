package com.springboot.admin.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求过滤器，封装request参数，用于多次通过从流中获取数据
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "requestFilter")
public class RequestFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            WrappedHttpServletRequest requestWrapper = new WrappedHttpServletRequest((HttpServletRequest) request);
//            if ("POST".equals(requestWrapper.getMethod().toUpperCase())) {
                // 获取请求参数
//                String params = requestWrapper.getRequestParams();
//                log.info("filer-post请求参数:[params={}]", params);
//            } else {
//                log.info("非post请求");
//            }

            // 这里doFilter传入我们实现的子类
            chain.doFilter(requestWrapper, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {

    }
}
