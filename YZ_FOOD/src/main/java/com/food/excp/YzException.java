package com.food.excp;




public class YzException extends Exception {

	
	
	
	private static final long serialVersionUID = 5641195630134175261L;
	private CODE error;
	private long param = 0;
	private Object paramObj=null;
	
	
	public YzException(CODE dataNull){
		this.error = dataNull;
	}
	
	public YzException(CODE error,long param){
		this.error = error;
		this.param = param;
	}
	public YzException(CODE error,Object paramObj){
		this.error = error;
		this.paramObj = paramObj;
	}
	
	
	public int getCode(){
		return error.getIndex();
	}
	public String getMsg(){
		return error.getMsg();
	}
	


	public long getParam() {
		return param;
	}
	public Object getParamObj() {
		return paramObj;
	}
	
}
