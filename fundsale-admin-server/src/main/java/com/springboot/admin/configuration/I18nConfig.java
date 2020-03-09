package com.springboot.admin.configuration;

import com.springboot.common.i18n.LocaleHeaderLocaleResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Created by zhenglian on 2016/10/12.
 */
@Configuration
//@Import(MessageUtils.class)
@ComponentScan(basePackages = {"com.springboot.common"})
public class I18nConfig {

    private final String LOCAL_HEAD_NAME = "lang";

    /**
     * 区域解析器
     *
     * @return
     */
    @Bean
//    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "spring.messages", name = "basename")
    public LocaleResolver localeResolver() {
        LocaleHeaderLocaleResolver localeResolver = new LocaleHeaderLocaleResolver();
        localeResolver.setLocaleHeadName(LOCAL_HEAD_NAME);
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        // 设置请求地址的参数,默认为 LocaleChangeInterceptor.DEFAULT_PARAM_NAME -> locale
        localeChangeInterceptor.setParamName(LOCAL_HEAD_NAME);
        return localeChangeInterceptor;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer1(LocaleChangeInterceptor localeChangeInterceptor) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(localeChangeInterceptor).order(-10);
            }
        };
    }
}
