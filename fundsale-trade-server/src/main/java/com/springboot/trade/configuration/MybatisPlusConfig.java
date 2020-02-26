package com.springboot.trade.configuration;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.springboot.common.reflection.wrapper.CustomMapWrapperFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author DGD
 * @date 2018/2/6.
 */
@Configuration
@EnableTransactionManagement
//@PropertySource(value = "classpath:mybatis-config.yml", factory = YamlPropertySourceFactory.class)
@MapperScan(basePackages = {"com.springboot.trade.mapper"})
public class MybatisPlusConfig {

    private static final String MAPPER_LOCATION = "classpath:mapper/*Mapper.xml";

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
//        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return new DruidDataSource();
//    }

//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setLogImpl(StdOutImpl.class);
//        sqlSessionFactory.setConfiguration(configuration);
//
//        return sqlSessionFactory.getObject();
//    }
//
//    /***
//     * SqlSessionTemplate与Spring事务管理一起使用，以确保使用的实际SqlSession是与当前Spring事务关联的,
//     * 此外它还管理会话生命周期，包括根据Spring事务配置根据需要关闭，提交或回滚会话
//     */
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    /**
//     * 事物管理器
//     *
//     * @return
//     */
//    @Bean(name = "transactionManager")
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
//        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(org.apache.ibatis.session.Configuration configuration) {
//                configuration.setObjectWrapperFactory(new CustomMapWrapperFactory());
//            }
//        };
        return i -> i.setObjectWrapperFactory(new CustomMapWrapperFactory());
    }

}
