package com.food.service;

import java.util.List;
import java.util.Map;

import com.food.excp.YzException;
import com.food.vo.FoodVo;
import com.food.vo.OrderVo;
import com.food.vo.UserVo;

/**  
* @author nimo
* @date 2018年1月10日 
* @version V1.0  
* @Description: 描述
*/
public interface CommService {
	
	void addUser(String nickname,String user) throws YzException;
	
	void deluser(String uid) throws YzException;
	
	Map<String,Object> login(String nickname,String password) throws YzException;
	
	void updatePassword(String user,String oldcode,String newcode) throws YzException;
	
	void addFood(String name) throws YzException;
	
	void delFood(String foodid) throws YzException;
	
	void order(String uid,String fid,String reason,String ip) throws YzException;
	
	void cancel(String uid,String fid) throws YzException;
	
	List<FoodVo>  foodlist() throws YzException;
	
	Map<String,Object>  orderlist() throws YzException;
	
	List<UserVo>  userlist() throws YzException;
	
	void sendEmail(int type);
	
}
