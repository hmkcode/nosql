package com.hmkcode.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="people")
public class Person {

	//Field annotated with "@Id" or named "id" will be mapped to document _id which is a required field.
	//so here we can add @Id or leave it.
	@Id
	private int id;
	private String name;
	private String[] friends;
	private Address address;
	
	public Person(int id,String name,String[] friends,Address address){
		this.id = id;
		this.name = name;
		this.friends = friends;
		this.address = address;
	}
	
	//instantiate Person with default friends and address
	public Person(int id,String name){
		this.id = id;
		this.name = name;
		String f[] = {"John","Brit","Tim"};
		this.friends = f;
		this.address = new Address("US","NY","New York");
	}
	public Person(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString(){
		String friendsArray = "";
		for(int f = 0 ; f < friends.length;f++)
			friendsArray += friends[f]+( (f+1) < friends.length?" , ":"");
		
		return "{id: "+this.id+", name: "+this.name+", friends: [ "+friendsArray+" ] , address: "+this.address+"}";
	}
}
