package com.springboot.product.configuration;

import com.springboot.common.i18n.LocaleHeaderLocaleResolver;
import com.springboot.common.util.MessageUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Created by zhenglian on 2016/10/12.
 */
@Configuration
@Import(MessageUtils.class)
public class I18nConfig {

    private final String LOCAL_HEAD_NAME = "lang";

    /**
     * 区域解析器
     * @return
     */
    @Bean
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
}
