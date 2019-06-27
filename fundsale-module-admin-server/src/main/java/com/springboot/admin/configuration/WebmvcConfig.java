package com.springboot.admin.configuration;


import com.springboot.admin.interceptor.ApproveInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ApproveInterceptor()).addPathPatterns("/**");
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**").allowedOrigins("http://localhost:8888");
//		//registry.addMapping("/api/**").allowedOrigins("http://localhost:8888");
//	}

}
