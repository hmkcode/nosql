package com.hmkcode.spring.data.neo4j.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmkcode.FriendMaker;
import com.hmkcode.spring.data.neo4j.entity.Person;
import com.hmkcode.spring.data.neo4j.repository.PersonRepository;



@Service
public class PersonService {
	
	@Autowired
	private	PersonRepository personRepository;
	
	public long count() {
		
		return personRepository.count();
	}
	
	public Person createPerson(String personId, String personName) {
		return personRepository.save(new Person(personId, personName));
	}
	
	public Iterable<Person> getAll() {
		return personRepository.findAll();
	}
	
	public Person findPersonById(Long id) {
		return personRepository.findOne(id);
	}
	
	public Person findWorldByName(String personName) {
		return personRepository.findByPropertyValue("personName", personName);
	}
	

	
	public void buildDataModel() {
		 int[][] friendsOf = FriendMaker.makeFriends();
		// create all persons
		for(int p = 0; p < 100;p++){
			 createPerson("id-"+(p+1),"name-"+(p+1)); // id-1, name-1
				System.out.println("Created person....."+(p+1));

		}
		// add friends
		Person person;
		for(int p = 0; p < 100;p++){
			person = findPersonById(new Long(p+1));
			for(int f = 0 ; f < 10; f++){
				person.addFriend(findPersonById(new Long(friendsOf[p][f])));
			}
			System.out.println("Created friends of...."+(p+1));

			personRepository.save(person);
		}	
   	}
	
}
