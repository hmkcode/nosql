package com.hmkcode.mongodb;
 
import java.net.UnknownHostException;
import java.util.Date;
import java.util.LinkedList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
 
/**
 * Java + MongoDB Simple Example
 * 
 */
public class Main {
	
	static String array_names[] = {"Jonh","Tim","Brit","Robin","Smith","Lora","Jennifer","Lyla","Victor","Adam"};
	static String array_address[][] ={
		{"US", "NY", "New York"},
		{"US", "NY", "Buffalo"},
		{"US", "CA", "Los Angeles"},
		{"US", "CA", "San Francisco"},
		{"US", "CA", "San Diego"},
		{"US", "NJ", "Newark"},
		{"US", "NJ", "Jersey City"},
		{"US", "FL", " Miami"},
		{"US", "FL", " Orlando"},
		{"US", "TX", " Houston"},
	};
  public static void main(String[] args) {
 
    try {
  
	// Connect to mongodb
	MongoClient mongo = new MongoClient("localhost", 27017);
 
	// get database 
	// if database doesn't exists, mongodb will create it for you
	DB db = mongo.getDB("test");
 
	// get collection
	// if collection doesn't exists, mongodb will create it for you
	DBCollection collection = db.getCollection("person");
 
	/**** Insert ****/
	// create a document to store key and value
	
	BasicDBObject document ;
	String address[];
	for(int i = 0 ; i < array_names.length ; i++){
		document = new BasicDBObject();
		//value -> String
		document.append("name", array_names[i]); 
		// value -> int
		document.append("age", (int)(Math.random()*60));
		// value -> date
		document.append("join", new Date());
		// value -> array
		document.append("friends", pickFriends()); 
		
		address = pickAddress();
		// value --> document
		document.append("address", new BasicDBObject("country",address[0])
									.append("State", address[1])
									.append("City", address[2])); 

		collection.insert(document);

	}
	
	
	// get count
	System.out.println("All Persons: "+collection.getCount());
	//------------------------------------
	// get all document
	DBCursor cursor = collection.find();
	try {
	   while(cursor.hasNext()) {
	       System.out.println(cursor.next());
	   }
	} finally {
	   cursor.close();
	}
	//------------------------------------

	// get documents by query
	BasicDBObject query = new BasicDBObject("age", new BasicDBObject("$gt", 30));

	cursor = collection.find(query);
	System.out.println("Person with age > 30 --> "+cursor.count());
	
 
	/**** Update ****/
	//update documents found by query "age > 30" with udpateObj "age = 20"
	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("age", 20);
 
	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);
 
	collection.update(query, updateObj,false,true);
 
	/**** Find and display ****/
	cursor = collection.find(query);
	System.out.println("Person with age > 30 after update --> "+cursor.count());
	
	
	//get all again
	cursor = collection.find();
	try {
	   while(cursor.hasNext()) {
	       System.out.println(cursor.next());
	   }
	} finally {
	   cursor.close();
	}
 
	/**** Done ****/
	System.out.println("Done");
 
    } catch (UnknownHostException e) {
	e.printStackTrace();
    } catch (MongoException e) {
	e.printStackTrace();
    }
 
  }
  //----------------------------------------------------
  //These methods are just jused to build random data
  private static String[] pickFriends(){
	  int numberOfFriends = (int) (Math.random()* 10);
	  LinkedList<String> friends = new LinkedList<String>();
	  int random = 0;
	  while(friends.size() < numberOfFriends){
		  random = (int) (Math.random()*10);
		  if(!friends.contains(array_names[random]))
			  friends.add(array_names[random]);
			  
	  }
	  String a[] = {};
	  return  friends.toArray(a);
  }
  private static String[] pickAddress(){
	  int random = (int) (Math.random()*10);
	  return array_address[random];
  }
}