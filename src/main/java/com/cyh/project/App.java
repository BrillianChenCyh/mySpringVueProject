package com.cyh.project;

import com.cyh.project.util.PropertiesUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author 25656
 */
@SpringBootApplication
//开启缓存
@EnableCaching
public class App {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		app.setDefaultProperties(PropertiesUtils.getInstance().getProperties());
		app.run(args);
		System.out.println("------启动成功-------");
	}

}
