package com.food.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**  
* @author nimo
* @date 2017年10月10日 
* @version V1.0  
* @Description:
*/
@Configuration
@MapperScan(basePackages="com.food.mapper",sqlSessionTemplateRef="commSqlSessionTemplate")
public class DataSourceConfig extends BaseDataConfig {
	    @Bean(name = "commDataSource")
	    @ConfigurationProperties(prefix = "spring.datasource")
	    @Primary
	    public DataSource commDataSource() {
	        return super.config(DruidDataSourceBuilder.create().build());
	    }

	    @Bean(name = "commSqlSessionFactory")
	    @Primary
	    public SqlSessionFactory commSqlSessionFactory(@Qualifier("commDataSource") DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	        bean.setDataSource(dataSource);
	       bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/food/mapper/*.xml"));
	        return bean.getObject();
	    }

	    @Bean(name = "commTransactionManager")
	    @Primary
	    public DataSourceTransactionManager commTransactionManager(@Qualifier("commDataSource") DataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }

	    @Bean(name = "commSqlSessionTemplate")
	    @Primary
	    public SqlSessionTemplate commSqlSessionTemplate(@Qualifier("commSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }

}
