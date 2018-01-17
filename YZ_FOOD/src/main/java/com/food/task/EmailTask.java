package com.food.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.food.service.CommService;



/**  
* @author nimo
* @date 2018年1月12日 
* @version V1.0  
* @Description: 描述
*/
@Component
public class EmailTask {

	
	@Autowired
	private CommService service;
		
	@Scheduled(cron = "0 0 16 ? * MON-FRI")
	public void sendEmail() {
		service.sendEmail(0);
		
	}
}
