package com.food.excp;



public enum CODE {

	
	ERROR("发生异常，请联系开发人员",-1), 
	USER_EXIST("用户已经存在",-2), 
	LOGIN_ERROR("用户名或者密码错误",-3), 
	LOGIN_TIMEOUT("请重新登录",-4), 
	FOOD_EXIST("菜品已经存在",-5),
	NO_AUTH("非法操作", -6),
	CHECK_ERROR("校验失败", -7),
	ORDER_EXIST("你已经点过了", -8),
	TIME_OUT("点餐时间为:中午12点到下午5点", -9),
	
	
    END("结束",   -999999);

    private String msg;
    private int index;

    // 构造方法
    private CODE(String msg, int index) {
        this.msg = msg;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getMsg() {
        return msg;
    }

}
