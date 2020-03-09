package com.cyh.project.config;

import com.alibaba.druid.pool.DruidDataSource;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SqlServerDruidConfig {
    private Logger logger = LoggerFactory.getLogger(SqlServerDruidConfig.class);

	@Bean(name = "ds2beetlSqlScannerConfigurer")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer(Environment env) {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		conf.setBasePackage("com.**.sqldao");
		conf.setDaoSuffix("SqlDao");
		conf.setSqlManagerFactoryBeanName("ds2sqlManagerFactoryBean");
		return conf;
	}

	@DependsOn({"ds2datasource"})
	@Bean(name = "ds2sqlManagerFactoryBean")
	public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("ds2datasource") DataSource ds2datasource, Environment env) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(ds2datasource);
		factory.setCs(source);
		factory.setDbStyle(selectDB(env.getProperty("spring.datasource.c.type")));
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

	@Bean(name = "ds2datasource")
	public DataSource druidDataSource(Environment env) throws SQLException {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDbType(env.getProperty("spring.datasource.c.type"));
        datasource.setUrl(env.getProperty("spring.datasource.c.url"));
        datasource.setUsername(env.getProperty("spring.datasource.c.username"));
        datasource.setPassword(env.getProperty("spring.datasource.c.password"));
        datasource.setDriverClassName(env.getProperty("spring.datasource.c.driver"));
        try {
            datasource.setFilters(env.getProperty("spring.datasource.c.type"));
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(env.getProperty("spring.datasource.c.type"));

        return datasource;

	}
}

