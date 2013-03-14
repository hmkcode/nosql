package com.hmkcode.spring.data.neo4j.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;


@NodeEntity
public class Person {

	
	@Indexed
	private String personId;
	private String personName;
	
	@GraphId
	private Long nodeId;
	
	 @RelatedTo(type = "FRIEND_OF", direction = Direction.OUTGOING)
	    private Set<Person> friends;

	public Person(String personId, String personName) {
		this.personId = personId;
	    this.personName = personName;
	}
	public Person(){}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public Set<Person> getFriends() {
		return friends;
	}
	public void setFriends(Set<Person> friends) {
		this.friends = friends;
	}
	
	public void addFriend(Person friend) {
		if(this.friends == null)
			this.friends = new HashSet<Person>();
		this.friends.add(friend);
	}
	
}
