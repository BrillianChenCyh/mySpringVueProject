package com.cyh.project.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.JdbcConstants;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.*;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *  @author: chenyinghui
 *  @Date: 2019/11/16 21:31
 *  @Description: 多数据源-数据库连接配置1
 */
@Configuration
public class DruidConfig1 {

    private Logger logger = LoggerFactory.getLogger(DruidConfig1.class);

    @Bean
	@Primary
    public ServletRegistrationBean druidServlet1() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow", ""); //白名单
        return reg;
    }

	@Bean
	@Primary
	public FilterRegistrationBean druidStatFilter1() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.setName("druidStatFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

	@Bean(name = "beetlSqlScannerConfigurer1")
	@Primary
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer1(Environment env) {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		conf.setBasePackage(env.getProperty("beetlsql.ds.a.basePackage"));
		conf.setDaoSuffix(env.getProperty("beetlsql.daoSuffix"));
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean1");
		return conf;
	}

	@DependsOn({"datasource1"})
	@Bean(name = "sqlManagerFactoryBean1")
	@Primary
	public SqlManagerFactoryBean getSqlManagerFactoryBean1(@Qualifier("datasource1") DataSource datasource, Environment env) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		System.out.println(env.toString());
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(selectDB(env.getProperty("spring.datasource.a.type")));
//		factory.setInterceptors(new Interceptor[0]);//不打印日志
		factory.setInterceptors(new Interceptor[] {new DebugInterceptor()});//打印日志
		factory.setNc(new UnderlinedNameConversion());
		factory.setSqlLoader(new ClasspathLoader(env.getProperty("beetlsql.sqlPath")));
		return factory;
	}

	private DBStyle selectDB(String style) {
		switch (style) {
		case JdbcConstants.MYSQL:
			return new MySqlStyle();
		case JdbcConstants.ORACLE:
			return new OracleStyle();
		case JdbcConstants.DB2:
			return new DB2SqlStyle();
		case JdbcConstants.SQL_SERVER:
			return new SqlServerStyle();
		case JdbcConstants.POSTGRESQL:
			return new PostgresStyle();
		case JdbcConstants.H2:
			return new H2Style();
		case JdbcConstants.SQLITE:
			return new SQLiteStyle();

		default:
			return null;
		}
	}

	@Bean(name = "datasource1")
	@Primary
	public DataSource druidDataSource1(Environment env) throws SQLException {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDbType(env.getProperty("spring.datasource.a.type"));
		datasource.setUrl(env.getProperty("spring.datasource.a.url"));
		datasource.setUsername(env.getProperty("spring.datasource.a.username"));
		datasource.setPassword(env.getProperty("spring.datasource.a.password"));
		datasource.setDriverClassName(env.getProperty("spring.datasource.a.driver"));
		try {
			datasource.setFilters(env.getProperty("spring.datasource.a.type"));
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(env.getProperty("spring.datasource.a.type"));
		return datasource;

	}

}

