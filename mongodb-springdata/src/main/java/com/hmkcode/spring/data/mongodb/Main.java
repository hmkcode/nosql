package com.hmkcode.spring.data.mongodb;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hmkcode.vo.Address;
import com.hmkcode.vo.Person;

public class Main {

	public static void main(String args[]){
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/hmkcode/spring/spring.xml");
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		
		Collection<Person> persons = new LinkedList<Person>();
		persons.add(new Person(2,"Scott"));
		persons.add(new Person(3,"Tim"));
		persons.add(new Person(4,"Lora"));

		
		//( 1 ) save one person
		// if collection is not specified in @Document(collection="people"), we need to specify collection name 
		// in save(document, collection);
		mongoOperation.save(new Person(1,"Brit"));
		//-------------------------------------------------------

		//( 2 ) insert many persons
		//if we have a list of different type of documents to be inserted, their collection will be 
		//specified by @Document(collection="collectionName")
		mongoOperation.insert(persons, Person.class);

		//-------------------------------------------------------
		//( 3 ) find inserted person
		Person savedPerson = mongoOperation.findOne(
					new Query(Criteria.where("name").is("Scott")), 
					Person.class
			        );
		
		System.out.println("saved Person: " + savedPerson);
		//-------------------------------------------------------
		//( 4 ) find all persons
		List<Person> allPersons = mongoOperation.findAll(Person.class);
		
		System.out.println("Number of persons = " + allPersons.size());
		//-------------------------------------------------------
		//( 5 ) updateMulti
		 // update
		  mongoOperation.updateMulti(
			new Query(Criteria.where("name").is("Scott")),
	        Update.update("address", new Address("US","CA","San Diego")),
	        Person.class
	     );
		//-------------------------------------------------------
		//( 6 ) find updated person
		Person updatedPerson = mongoOperation.findOne(
						new Query(Criteria.where("name").is("Scott")), 
						Person.class
				        );
			
		System.out.println("Updated Person: " + updatedPerson);
		//-------------------------------------------------------
		//( 7 ) remove person
		mongoOperation.remove(new Query(),Person.class);
			
		System.out.println("Numober persons: " + mongoOperation.findAll(Person.class).size());
	}
}
