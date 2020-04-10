package com.springboot.trade.configuration;

import com.springboot.trade.plugins.MybatisSqlPrintInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;


@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
public class MybatisSqlPrintAutoConfiguration {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    /**
     * sql 打印需要在拦截器最后一个才能统计 {@literal https://github.com/pagehelper/pagehelper-spring-boot}
     */
    @Configuration
    @ConditionalOnExpression("${mybatis.sql.inteceptor:true}")
    public class AutoConfigPrintInterceptor {
        @PostConstruct
        public void addPrintInterceptor() {
            MybatisSqlPrintInterceptor printInterceptor = new MybatisSqlPrintInterceptor();
            for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
                sqlSessionFactory.getConfiguration().addInterceptor(printInterceptor);
            }
        }
    }


}
