/**  
 *   Copyright 2021 cc01cc
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

/**
*   @Title: WebConfig.java
*   @Description: TODO 
*   @author cc01cc
*   @date 2021-11-20 
*/

package com.cc01cc.spring.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.cc01cc.spring.interceptor.BaseInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author cc01cc
 * @date 2021-11-21
 * @Description: 网页配置
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.cc01cc.spring.controller", "com.cc01cc.spring.service",
        "com.cc01cc.spring.aspectj", "com.cc01cc.spring.interceptor" })
@MapperScan("com.cc01cc.spring.mapper")
// 开启事务处理支持
@EnableTransactionManagement
// 开启 AspectJ 支持
@EnableAspectJAutoProxy
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
        super();
    }

    private ApplicationContext applicationContext;

    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    /* ******************************************************************* */
    /* GENERAL CONFIGURATION ARTIFACTS */
    /* Static Resources, i18n Messages, Formatters (Conversion Service) */
    /* ******************************************************************* */

    // 配置静态资源
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // addResourceHandler 接收客户端的请求参数
        // addResourceLocations 从本地寻找相关文件
        /** classpath:/META-INF/public-web-resources/ **/

        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("Messages");
        return messageSource;
    }

    /* 配置 thymeleaf 的视图解析器 */

    /* **************************************************************** */
    /* THYMELEAF-SPECIFIC ARTIFACTS */
    /* TemplateResolver <- TemplateEngine <- ViewResolver */
    /* **************************************************************** */

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        // SpringResourceTemplateResolver automatically integrates with Spring's
        // own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
        // speed up execution in most scenarios, but might be incompatible
        // with specific cases when expressions in one template are reused
        // across different data types, so this flag is "false" by default
        // for safer backwards compatibility.
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        System.out.println("Thymeleaf 视图解析器");
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        System.out.print("Thymeleaf初始化完成");
        return viewResolver;
    }

    // 创建HikariCP数据源
    @Bean
    public DataSource dataSource() {
        HikariConfig     config = new HikariConfig("/hikari_mysql.properties");
        HikariDataSource ds     = new HikariDataSource(config);
        return ds;
    }

    // 使用 SqlSessionFactoryBean 注册 SqlSessionFacotry
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cc01cc.spring.pojo");
        PathMatchingResourcePatternResolver resolver        = new PathMatchingResourcePatternResolver();
        String                              mapperLocations = "classpath:/com/cc01cc/spring/mapper/*.xml";
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        return sqlSessionFactoryBean.getObject();
    }

    // @Bean
    // public MapperFactoryBean<BaseMapper> baseMapper(){
    // MapperFactoryBean<BaseMapper> factoryBean = new
    // MapperFactoryBean<>(BaseMapper.class);
    // facotoryBean.setSqlSessionFactory(sqlSessionFactory());
    // return factoryBean;
    // }

    // 开启spring事务管理
    // https://mybatis.org/spring/zh/transactions.html
    // 确保 dataSource 与 SqlSenssionFactoryBean 一致
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    // 配置 multipartResolver
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(1024 * 1024 * 1024);
        return multipartResolver;
    }

    // 配置 拦截器

    /**
     * <p>
     * Title: addInterceptors
     * </p>
     * <p>
     * Description:
     * </p>
     * @param registry
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 在 config 里是无法使用 @Autowired 标签吗，这边引用也失败
        BaseInterceptor baseInterceptor = new BaseInterceptor();
        registry.addInterceptor(baseInterceptor).addPathPatterns("/**");
    }
}
