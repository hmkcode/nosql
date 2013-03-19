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
public class Query {
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
 
	DBCursor cursor;
	BasicDBObject query;
	//------------------------------------
	// ( 1 ) collection.find() --> get all document
	cursor = collection.find();
	System.out.println("( 1 ) .find()");
	System.out.println("results --> "+cursor.count());
	try {
	   while(cursor.hasNext()) {
	       System.out.println(cursor.next());
	   }
	} finally {
	   cursor.close();
	}
    System.out.println("---------------------------------");
	//------------------------------------
	// ( 2 ) collection.find({"age" : {"$gt" : 10}})) --> get documents by query
	query = new BasicDBObject("age", new BasicDBObject("$gt", 10));
	cursor = collection.find(query);
	System.out.println("( 2 ) .find({\"age\" : {\"$gt\" : 10}})");
	System.out.println("results --> "+cursor.count());
    System.out.println("---------------------------------");
	//------------------------------------
	// ( 3 ) collection.find({"age" : {"$gt" : 10}}, {"name" : 1, "age" : 1,"_id":0}) --> get documents with some keys
	BasicDBObject keys = new BasicDBObject("name", 1).append("age", 1).append("_id", 0);
	cursor = collection.find(query, keys);
	System.out.println("( 3 ) .find({\"age\" : {\"$gt\" : 10}}, {\"name\" : 1, \"age\" : 1,\"_id\":0})");
	System.out.println("results --> "+cursor.count());
	try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
	System.out.println("---------------------------------");
	//-----------------------------------	
 
	/**** Done ****/
	System.out.println("Done");
 
    } catch (UnknownHostException e) {
	e.printStackTrace();
    } catch (MongoException e) {
	e.printStackTrace();
    }
 
  }
}