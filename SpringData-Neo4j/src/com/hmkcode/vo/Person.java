package com.hmkcode.vo;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;



@NodeEntity
public class Person {
	

	@GraphId
	private Long nodeId;
	
	@Indexed
	private String id;
	private String name;

	public Person(){}
	
	@RelatedTo(type = "KNOW", direction = Direction.BOTH)
	private Set<Person> friends;

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getFriends() {
		return friends;
	}

	public void setFriends(Set<Person> friends) {
		this.friends = friends;
	}
	
	public void addFriend(Person person){
		if(friends == null)
			friends = new HashSet<Person>();
		friends.add(person);
	}
	
	
	
	
	
	
}
