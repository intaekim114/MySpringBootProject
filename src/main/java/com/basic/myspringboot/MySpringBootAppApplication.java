package com.basic.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 굉장히 많은 일을 하고 있음
public class MySpringBootAppApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MySpringBootAppApplication.class, args);
		SpringApplication application = new SpringApplication(MySpringBootAppApplication.class); // 객체 직접 생성
		// 기본적으로 WebApplicationType은 웹 어플리케이션이다.
		application.setWebApplicationType (WebApplicationType.SERVLET ); // 웹 앱의 기본적인 타입은 SERVLET이다.

		application.run (args);
	}

}
