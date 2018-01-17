package com.food.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;

import com.food.vo.UserVo;









/**  
 * @author nimo
 * @date 2016-3-23 
 * @version V1.0  
 * @Description: 邮件发送
 */
public class EmailUtil {
		/**STMP信息**/
	    private static final String STMP_SERVER="smtp.exmail.qq.com";   
	    private static final String STMP_PORT="465"; 
	    /**STMP账户**/
	    private static final String STMP_USERNAME="system@yazhai.co";
	    private static final String STMP_PASSWORD="Yazhai666";
	    /**发件人信息**/
	    private static final String SEND_MAIL="system@yazhai.co";
	    private static final String SEND_NAME="点餐系统";

	    /**
	     * 发送
	     *@param message
	     *@param title
	     *@param emails
	     *@return
	     */
		public static boolean send(String message,String title,List<String> emails){
			boolean result=false;
			HtmlEmail email=new HtmlEmail();
			try {
				email.setHostName(STMP_SERVER);
				//465错误时 改为了false
				email.setSSLOnConnect(false);
				email.setSslSmtpPort(STMP_PORT);
				email.setCharset("UTF-8");
				for (String url : emails) {
					if (!StringUtils.isBlank(url)) {
						email.addTo(url);
					}
				}
				email.setFrom(SEND_MAIL,SEND_NAME);
				email.setAuthentication(STMP_USERNAME, STMP_PASSWORD);
				email.setSslSmtpPort(STMP_PORT);
				email.setSubject(title);
				email.setMsg(message);
				email.send();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * 发送系统告警
		 *@param title  标题
		 *@param content 内容
		 *@param level  等级 1错误2警告3-4普通5恢复
		 *@param detail  详细信息
		 *@param person  负责人员
		 *@param emails  邮件发送地址列表
		 *@return
		 */
		public static boolean sendSystemMail(List<String> emails){
			StringBuffer sb=new StringBuffer();
			sb.append("<div style='font-family:Microsoft Yahei,Tahoma,Arial;position:relative;" +
					"margin:50px auto 0;height:auto;max-width:50pc;border:1px solid #ccc;background:#FAFAFA;'>");
			sb.append("<h3 style='margin:0;height:40px;background:#32CD32;") ;
			sb.append("color:#fff;text-align:center;font-size:18px;line-height:40px;font-weight:normal'>准备点餐啦</h3>");
			sb.append("<div style='overflow:hidden;margin:10px auto;width:90%'>");
			sb.append("<div style='margin-top:10px;color:#4a708b;font-size:16px;'><a href='http://192.168.1.20:8080'>进入点餐系统</a></div>");
			sb.append("<div style='width:100%;color:rgba(0,0,0,.5);text-align:right;font-size:9pt;heigth:20px'>压寨系统邮件 请勿回复</div></div></div>");
			if (emails.size()<=0) {
				return false;
			}
			send(sb.toString(), "[准备点餐啦]",emails);
			return true;
		}
	
		
		public static boolean sendMail(List<UserVo> emails){
			String h4="<h4 style='width:100%;height:25px;font-size:1pc;line-height:25px;font-weight:normal;margin:0;'>";
			for (UserVo userVo : emails) {
				List<String> e =new ArrayList<String>();
				if(userVo.getEmail()==1){
					e.add(userVo.getUser()+"@yazhai.co");
					StringBuffer sb=new StringBuffer();
					sb.append("<div style='font-family:Microsoft Yahei,Tahoma,Arial;position:relative;" +
							"margin:50px auto 0;height:auto;max-width:50pc;border:1px solid #ccc;background:#FAFAFA;'>");
					sb.append("<h3 style='margin:0;height:40px;background:#32CD32;") ;
					sb.append("color:#fff;text-align:center;font-size:18px;line-height:40px;font-weight:normal'>准备点餐啦</h3>");
					sb.append("<div style='overflow:hidden;margin:10px auto;width:90%'>");
					sb.append(h4+"登录账户: <span style='color:#858585;font-size:15px'>"+userVo.getUser()+"</span></h4>");
					sb.append(h4+"登录密码: <span  style='color:#858585;font-size:15px'>"+userVo.getPassword()+"</span></h4>");
					sb.append("<div style='margin-top:10px;color:#4a708b;font-size:16px;'><a href='http://192.168.1.20:8080'>进入点餐系统</a></div>");
					sb.append("<div style='width:100%;color:rgba(0,0,0,.5);text-align:right;font-size:9pt;heigth:20px'>压寨系统邮件 请勿回复</div></div></div>");
					if (emails.size()<=0) {
						return false;
					}
					send(sb.toString(), "[准备点餐啦]",e);
				}
			}
			return true;
		}
		
		
	
		
		public static void main(String[] args) {
			List<String> emails =new ArrayList<String>();
			emails.add("nimo@yazhai.co");
			sendSystemMail(emails);
		}
}
