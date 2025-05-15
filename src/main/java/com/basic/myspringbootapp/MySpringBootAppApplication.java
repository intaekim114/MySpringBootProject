package com.basic.myspringbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootAppApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MySpringBootAppApplication.class, args);
		SpringApplication application = new SpringApplication(MySpringBootAppApplication.class);
		application.setWebApplicationType (WebApplicationType.NONE );
		application.run (args);
	}

}
