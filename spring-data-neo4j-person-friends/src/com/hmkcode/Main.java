package com.hmkcode;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hmkcode.spring.data.neo4j.service.PersonService;




public class Main{
	
	
	public Main(){
	}
	

	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("com/hmkcode/spring/config/spring_aspects.xml");
		PersonService personService = (PersonService) context.getBean("personService");
		personService.buildDataModel();
		System.out.println("Count: "+personService.count());
		

	}
	
	
}