package com.hmkcode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hmkcode.springdata.neo4j.GraphDB;

public class Main{
	
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("com/hmkcode/spring/spring.xml");
		GraphDB db = (GraphDB)context.getBean("graphDB");
		db.buildDataModel();

	}
	
}