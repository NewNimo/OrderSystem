package com.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




/**  
* @author nimo
* @date 2017年9月26日 
* @version V1.0  
* @Description: 启动入口
*/

@SpringBootApplication //springboot初始化
@Controller //开启REST
@EnableScheduling //开启定时任务
public class Application  {
	@Bean
	public EmbeddedServletContainerCustomizer errorProcess(){
		return new EmbeddedServletContainerCustomizer() {
			//定制500，404页面
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage page404=new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage page500=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
				container.addErrorPages(page404,page500);
			}
		};
	}
  
	//主页
	@RequestMapping(value="/")
	ModelAndView index(){
		return new ModelAndView("index.html");
	}

	
	//程序入口
	public static void main(String[] args) throws Exception{
		SpringApplication app=new SpringApplication(Application.class);
		app.run(args);
	}
}
