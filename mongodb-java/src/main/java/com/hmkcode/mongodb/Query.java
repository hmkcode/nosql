package com.hmkcode.mongodb;
 
import java.net.UnknownHostException;
import java.util.Date;
import java.util.regex.Pattern;

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
    String[] comparison_operators = {"$gt","$lt","$gte","$lte","$ne"};
    for(int p = 0 ; p < comparison_operators.length; p++){
    	query = new BasicDBObject("age", new BasicDBObject(comparison_operators[p], 20));
    	cursor = collection.find(query);
    	System.out.println("( 2."+(p+1)+" ) .find({\"age\" : {\""+comparison_operators[p]+"\" : 20}})");
    	System.out.println("results --> "+cursor.count());
    }
	System.out.println("---------------------------------");
	//------------------------------------
	// ( 3 ) collection.find({"name" : /j/}, {"name" : 1, "age" : 1,"_id":0}) --> get documents with some keys
	Pattern j = Pattern.compile("j", Pattern.CASE_INSENSITIVE);
	query = new BasicDBObject("name", j);
	BasicDBObject keys = new BasicDBObject("name", 1).append("age", 1).append("_id", 0);
	cursor = collection.find(query, keys);
	System.out.println("( 3 ) .find({\"name\" : /j/i}, {\"name\" : 1, \"age\" : 1,\"_id\":0})");
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
	// ( 4 ) collection.find({"age" : {"$in" : [10,20,30]}})) --> get documents by query
    String[] or_operators = {"$in","$nin"};
    int[] age_array = {10,20,30};
    for(int p = 0 ; p < or_operators.length; p++){
    	query = new BasicDBObject("age", new BasicDBObject(or_operators[p], age_array));
    	cursor = collection.find(query);
    	System.out.println("( 4."+(p+1)+" ) .find({\"age\" : {\""+or_operators[p]+"\" : [10,20,30]}})");
    	System.out.println("results --> "+cursor.count());
    }
	System.out.println("---------------------------------");
	//-----------------------------------	
	// ( 5 ) collection.find({"$or" : [ {"name":"John" },{"age":20}]}) --> get documents by query
	BasicDBObject or_conditions[] = {new BasicDBObject("name", "John"),new BasicDBObject("age", 20)};
    query = new BasicDBObject("$or", or_conditions);
    cursor = collection.find(query);
    System.out.println("( 5 ) .find({\"$or\" :[{\"name\":\"John\"},{\"age\":20}]})");
    System.out.println("results --> "+cursor.count());
	System.out.println("---------------------------------");
	//-----------------------------------	
	// ( 6 ) collection.find({"friends" : ["John","Tim"]},{"name" : 1, "freinds" : 1,"_id":0})) --> get documents by query
	String[] friends = {"John","Tim"};
    query = new BasicDBObject("friends", new BasicDBObject("$all",friends) );
    keys = new BasicDBObject("name", 1).append("friends", 1).append("_id", 0);
    cursor = collection.find(query,keys);
    System.out.println("( 6 ) .find({\"friends\" : {$all:[\"John\",\"Tim\"]}},{\"name\" : 1, \"freinds\" : 1,\"_id\":0})");
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
	// ( 7 ) collection.find({"address" : {"country":"US","state":"NY","city":"Buffalo"}},{"name" : 1, "address" : 1,"_id":0})) --> get documents by query
	    query = new BasicDBObject("address", new BasicDBObject("country","US").append("state","NY").append("city","Buffalo"));
	    keys = new BasicDBObject("name", 1).append("address", 1).append("_id", 0);
	    cursor = collection.find(query,keys);
	    System.out.println("( 7 ) .find({\"address\" : {\"country\":\"US\",\"state\":\"NY\",\"city\":\"Buffalo\"}},{\"name\" : 1, \"address\" : 1,\"_id\":0})");
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
	// ( 8 ) collection.find({"address.state" : "NY"},{"name" : 1, "address" : 1,"_id":0})) --> get documents by query
    query = new BasicDBObject("address.state", "NY");
    keys = new BasicDBObject("name", 1).append("address", 1).append("_id", 0);
    cursor = collection.find(query,keys);
    System.out.println("( 8 ) .find({\"address.state\" : \"NY\"},{\"name\" : 1, \"address\" : 1,\"_id\":0})");
    System.out.println("results --> "+cursor.count());
    try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		System.out.println("---------------------------------");
	/**** Done ****/
	System.out.println("Done");
 
    } catch (UnknownHostException e) {
	e.printStackTrace();
    } catch (MongoException e) {
	e.printStackTrace();
    }
 
  }
}