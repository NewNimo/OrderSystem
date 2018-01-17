package com.food.config;


import java.util.ArrayList;

import com.alibaba.druid.pool.DruidDataSource;

/**  
* @author nimo
* @date 2017年11月4日 
* @version V1.0  
* @Description: 
*/
public abstract class BaseDataConfig {
	
	/**
	 * 配置一些Druid连接池参数 可以自行调整
	 *@param source
	 *@return
	 */
	public DruidDataSource config(DruidDataSource source){
		 // 配置初始化大小、最小、最大 
		 source.setMaxActive(50);
		 source.setInitialSize(3);
		 source.setMinIdle(3);
		 // maxWait获取连接等待超时的时间
		 source.setMaxWait(60000);
		 //timeBetweenEvictionRunsMillis间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		 source.setTimeBetweenEvictionRunsMillis(60000);
		 //minEvictableIdleTimeMillis一个连接在池中最小空闲的时间，单位是毫秒
		 source.setMinEvictableIdleTimeMillis(300000);
		 source.setValidationQuery("SELECT 'z'");
		 source.setTestWhileIdle(true);
		 source.setTestOnBorrow(false);
		 source.setTestOnReturn(false);
		 source.setRemoveAbandoned(true);
		 source.setRemoveAbandonedTimeout(180);
		 ArrayList<String> sqls=new ArrayList<String>();
		 sqls.add("SET NAMES utf8");
		 source.setConnectionInitSqls(sqls);
		 return source;
	}
	
	
	
   
    
}
