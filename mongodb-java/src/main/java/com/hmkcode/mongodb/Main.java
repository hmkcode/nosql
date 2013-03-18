package com.hmkcode.mongodb;
 
import java.net.UnknownHostException;
import java.util.Date;
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
	for(int i = 0 ; i < 10 ; i++){
		document = new BasicDBObject();
		document.append("name", "person-"+i);
		document.append("age", (int)(Math.random()*60));
		document.append("join", new Date());
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
}