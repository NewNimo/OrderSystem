  package com.food.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.excp.YzException;
import com.food.service.CommService;




/**
 * 
* @author nimo
* @date 2017年9月25日 
* @version V1.0  
* @Description: 用户信息
 */
@Controller
@RequestMapping("/food")
@Scope("prototype")
public class FoodAction extends AbstractAction{
	
	@Autowired
	private CommService commService;
	
	//增加用户
	@RequestMapping(value="/user/add")
	@ResponseBody
	@CrossOrigin
	String useradd(HttpServletRequest request,String nickname,String user){
		try {
			commService.addUser(nickname, user);
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	//删除用户
	@RequestMapping(value="/user/del")
	@ResponseBody
	@CrossOrigin
	String userdel(HttpServletRequest request,String uid){
		try {
			commService.deluser(uid);
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	//用户登录
	@RequestMapping(value="/user/login")
	@ResponseBody
	@CrossOrigin
	String login(HttpServletRequest request,String user,String password){
		try {
			data.putAll(commService.login(user, password));
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	
	//修改密码
	@RequestMapping(value="/user/updatepassword")
	@ResponseBody
	@CrossOrigin
	String updatepassword(HttpServletRequest request,String user,String newpassword,String password){
		try {
			commService.updatePassword(user, password, newpassword);
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	//菜品添加
	@RequestMapping(value="/food/add")
	@ResponseBody
	@CrossOrigin
	String foodadd(HttpServletRequest request,String name){
		try {
			commService.addFood(name);
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	
	//菜品删除
	@RequestMapping(value="/food/del")
	@ResponseBody
	@CrossOrigin
	String fooddel(HttpServletRequest request,String foodid){
		try {
			commService.delFood(foodid);
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	
	//点餐
	@RequestMapping(value="/food/order")
	@ResponseBody
	@CrossOrigin
	String foodorder(HttpServletRequest request,String uid,String foodid,String reason){
		try {
			String ip = request.getRemoteAddr();
			commService.order(uid, foodid, reason,ip);
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	//取消
	@RequestMapping(value="/food/cancel")
	@ResponseBody
	@CrossOrigin
	String foodcancel(HttpServletRequest request,String uid,String foodid){
		try {
			commService.cancel(uid, foodid);
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	
	//菜单列表
	@RequestMapping(value="/food/list")
	@ResponseBody
	@CrossOrigin
	String foodlist(HttpServletRequest request){
		try {
			data.put("list", commService.foodlist());
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	//订单系统
	@RequestMapping(value="/order/list")
	@ResponseBody
	@CrossOrigin
	String orderlist(HttpServletRequest request){
		try {
			data.putAll(commService.orderlist());
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	
	
	@RequestMapping(value="/user/list")
	@ResponseBody
	@CrossOrigin
	String userlist(HttpServletRequest request){
		try {
			data.put("list", commService.userlist());
			SUCCESS();
		} catch (YzException e) {
			FAIL(e);	
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
	
	@RequestMapping(value="/email")
	@ResponseBody
	@CrossOrigin
	String email(HttpServletRequest request,Integer type){
		try {
			commService.sendEmail(type);
			SUCCESS();
		} catch (Exception e) {
			ERROR();
			e.printStackTrace();
		}
		return json();
	}
}
