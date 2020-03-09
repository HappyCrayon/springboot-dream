package com.springboot.admin.configuration;


import com.springboot.admin.interceptor.ApproveFlowInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//审批流拦截器
		registry.addInterceptor(new ApproveFlowInterceptor()).addPathPatterns("/**");

//		registry.addInterceptor(localeChangeInterceptor);
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**").allowedOrigins("http://localhost:8888");
//		//registry.addMapping("/api/**").allowedOrigins("http://localhost:8888");
//	}

}
