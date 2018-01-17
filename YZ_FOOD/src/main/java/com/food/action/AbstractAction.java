package com.food.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.alibaba.fastjson.JSONObject;
import com.food.excp.YzException;




@Scope("prototype")
public class AbstractAction {

	protected static Logger logger;
	
	public AbstractAction() {
		logger = Logger.getLogger(this.getClass());
	}
	protected Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * 返回未加密数据
	 *@return
	 */
	public String json() {
		String json = JSONObject.toJSONString(data);
		return json;
	}

	
	/**
	 * 成功
	 */
	protected void SUCCESS() {
		data.put("code", 1);
		data.put("msg", "success");  
	}
	/**
	 * 错误
	 *@param code
	 */
	protected void ERROR() {
		data.clear();
		data.put("code", -1);
		data.put("msg", "error");
	}
	
	/**
	 * 异常
	 *@param code
	 *@param msg
	 */
	protected void FAIL(int code,String msg) {
		data.clear();
		data.put("code", code);
		data.put("msg", msg);
	}
	/**
	 *  异常
	 *@param YzException
	 */
	protected void FAIL(YzException e) {
		data.clear();
		data.put("code", e.getCode());
		data.put("msg", e.getMsg());
	}
	
	
}
