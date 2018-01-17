package com.food.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.food.vo.FoodVo;
import com.food.vo.OrderVo;
import com.food.vo.UserVo;

/**  
* @author nimo
* @date 2018年1月10日 
* @version V1.0  
* @Description: 描述
*/
public interface CommDao {
	
	Integer getUser(@Param("user") String user);
	
	Integer getUserByid(@Param("uid") String uid);
	
	int addUser(@Param("nickname") String nickname,@Param("user") String user,@Param("password") String password);
	
	int deluser(String uid);
	
	UserVo login(@Param("user") String user,@Param("password") String password);
	
	int updatePassword(@Param("user")String user,@Param("password") String password);
	
	Integer getFood(String name);
	
	int addFood(String name);
	
	int delFood(String foodid);
	
	Integer getOrder(String uid);
	
	int order(@Param("uid")String uid,@Param("foodid") String fid,@Param("reason") String reason,@Param("ip") String ip);
	
	int cancel(@Param("uid") String uid, @Param("foodid") String fid);
	
	List<FoodVo>  foodlist();
	
	List<OrderVo>  orderlist();
	
	List<UserVo>  userlist();
	
	List<FoodVo> foodfinal();
}
