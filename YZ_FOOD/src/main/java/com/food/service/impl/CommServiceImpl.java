package com.food.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.Utils.EmailUtil;
import com.food.excp.CODE;
import com.food.excp.YzException;
import com.food.mapper.CommDao;
import com.food.service.CommService;
import com.food.vo.FoodVo;
import com.food.vo.OrderVo;
import com.food.vo.UserVo;

/**  
* @author nimo
* @date 2018年1月10日 
* @version V1.0  
* @Description: 描述
*/
@Service
public class CommServiceImpl implements CommService {

	@Autowired
	private CommDao dao;
	
	/**
	*@param nickname
	*/
	
	@Override
	public void addUser(String nickname,String user) throws YzException {
		if(StringUtils.isBlank(nickname)||StringUtils.isBlank(user)){
			throw new YzException(CODE.NO_AUTH);
		}
		if(dao.getUser(user)>0){
			throw new YzException(CODE.USER_EXIST);
		}
		Integer f=  (int) (Math.random()*9000+1000);
		String password=f.toString();
		dao.addUser(nickname, user,password);
	}

	/**
	*@param uid
	*/
	
	@Override
	public void deluser(String uid) throws YzException  {
		int i=dao.deluser(uid);
		if(i==0){
			throw new YzException(CODE.ERROR);
		}
	}

	/**
	*@param nickname
	*@param password
	*/
	
	@Override
	public Map<String,Object> login(String user, String password) throws YzException  {
		UserVo vo=dao.login(user, password);
		if(vo==null){
			throw new YzException(CODE.LOGIN_ERROR);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("user",user);
		map.put("uid",vo.getUid());
		if(user.equals("admin")){
			map.put("url","administratorkaerwangadiking");
		}
		map.put("nickname", vo.getNickname());
		return map;
	}

	/**
	*@param uid
	*@param password
	*/
	
	@Override
	public void updatePassword(String user,String oldcode,String newcode) throws YzException  {
		UserVo vo=dao.login(user, oldcode);
		if(vo==null){
			throw new YzException(CODE.CHECK_ERROR);
		}
		dao.updatePassword(user, newcode);
	}

	/**
	*@param nickname
	*/
	
	@Override
	public void addFood(String name)  throws YzException {
		if(StringUtils.isBlank(name)){
			throw new YzException(CODE.NO_AUTH);
		}
		if(dao.getFood(name)>0){
			throw new YzException(CODE.FOOD_EXIST);
		}
		dao.addFood(name);
	}

	/**
	*@param foodid
	*/
	
	@Override
	public void delFood(String foodid) throws YzException  {
		int i=dao.delFood(foodid);
		if(i==0){
			throw new YzException(CODE.ERROR);
		}
	}

	/**
	*@param uid
	*@param fid
	*@param reason
	*/
	
	@Override
	public void order(String uid, String fid, String reason,String ip) throws YzException  {
		if(StringUtils.isBlank(uid)||StringUtils.isBlank(fid)||StringUtils.isBlank(reason)){
			throw new YzException(CODE.ERROR);
		}
		if(dao.getOrder(uid)>0){
			throw new YzException(CODE.ORDER_EXIST);
		}
		Integer u=dao.getUserByid(uid);
		if(u==null||u.intValue()==0){
			throw new YzException(CODE.NO_AUTH);
		}
		Calendar c=Calendar.getInstance();
		int i=c.get(Calendar.HOUR_OF_DAY);
		if(i>12&&i<17){
			dao.order(uid, fid, reason,ip);
		}else{
			throw new YzException(CODE.TIME_OUT);
		}
		
	}

	/**
	*@param uid
	*@param fid
	*/
	
	@Override
	public void cancel(String uid, String fid) throws YzException  {
		dao.cancel(uid, fid);
	}

	/**
	*@return
	*/
	
	@Override
	public List<FoodVo> foodlist() throws YzException  {
		return dao.foodlist();
	}

	/**
	*@return
	*/
	
	public Map<String,Object> orderlist()  throws YzException {
		Map<String,Object> map=new HashMap<String,Object>();
		List<OrderVo> list=dao.orderlist();
		map.put("list", list);
		map.put("total", dao.foodfinal());
		return map;
	}

	/**
	*@return
	*@throws YzException
	*/
	
	public List<UserVo> userlist() throws YzException {
		return dao.userlist();
	}

	/**
	*
	*/
	
	@Override
	public void sendEmail(int type) {
		try {
			List<UserVo> list=userlist();
			List<String> emails=new ArrayList<String>();
			if(type==0){
				if(list.size()>0){
					for (UserVo userVo : list) {
						if(userVo.getEmail()==1){
							emails.add(userVo.getUser()+"@yazhai.co");
						}
					}
					if(emails.size()>0){
						EmailUtil.sendSystemMail(emails);
					}
				}
			}else{
				EmailUtil.sendMail(list);
			}
		} catch (YzException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
