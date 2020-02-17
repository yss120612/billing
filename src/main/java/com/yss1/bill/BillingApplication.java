package com.yss1.bill;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@SpringBootApplication
public class BillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

	 @Bean
	  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {

	      System.out.println("Let's inspect the beans provided by Spring Boot:");

	      String[] beanNames = ctx.getBeanDefinitionNames();
	      Arrays.sort(beanNames);
	      for (String beanName : beanNames) {
	        System.out.println(beanName);
	      }
	      System.out.println("End of list");
	      System.out.flush();
	    };
	  }
	 
	
		
	 
	
}
