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
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 *  @author: chenyinghui
 *  @Date: 2019/11/16 21:31
 *  @Description: 多数据源-数据库连接配置2
 */
@Configuration
public class DruidConfig2 {
    private Logger logger = LoggerFactory.getLogger(DruidConfig2.class);

    @Bean
    public ServletRegistrationBean druidServlet2() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow", ""); //白名单
        return reg;
    }

	@Bean
	public FilterRegistrationBean druidStatFilter2() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.setName("druidStatFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

	@Bean(name = "beetlSqlScannerConfigurer2")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer2(Environment env) {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		conf.setBasePackage(env.getProperty("beetlsql.ds.b.basePackage"));
		conf.setDaoSuffix(env.getProperty("beetlsql.daoSuffix"));
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean2");
		return conf;
	}

	@DependsOn({"datasource2"})
	@Bean(name = "sqlManagerFactoryBean2")
	public SqlManagerFactoryBean getSqlManagerFactoryBean2(@Qualifier("datasource2") DataSource datasource, Environment env) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		System.out.println(env.toString());
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(selectDB(env.getProperty("spring.datasource.b.type")));
//		factory.setInterceptors(new Interceptor[0]);
		factory.setInterceptors(new Interceptor[] {new DebugInterceptor()});
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

	@Bean(name = "datasource2")
	public DataSource druidDataSource2(Environment env) throws SQLException {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDbType(env.getProperty("spring.datasource.b.type"));
		datasource.setUrl(env.getProperty("spring.datasource.b.url"));
		datasource.setUsername(env.getProperty("spring.datasource.b.username"));
		datasource.setPassword(env.getProperty("spring.datasource.b.password"));
		datasource.setDriverClassName(env.getProperty("spring.datasource.b.driver"));
		try {
			datasource.setFilters(env.getProperty("spring.datasource.b.type"));
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter", e);
		}
		datasource.setConnectionProperties(env.getProperty("spring.datasource.b.type"));
		return datasource;

	}

}

