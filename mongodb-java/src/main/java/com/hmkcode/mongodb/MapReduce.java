package com.hmkcode.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;
 

 
/**
 * Java + MongoDB MapReduce  count()
 * 
 */
public class MapReduce {
	

	
  public static void main(String[] args) {
 
  
	// Connect to mongodb
	MongoClient mongo = null;
	try {
		mongo = new MongoClient("localhost", 27017);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
	// get database 
	// if database doesn't exists, mongodb will create it for you
	DB db = mongo.getDB("test");
 
	// get collection
	// if collection doesn't exists, mongodb will create it for you
	DBCollection collection = db.getCollection("person");
 
	String map ="function () {"+
			"emit('size', {count:1});"+
  	"}";
	
	String reduce = "function (key, values) { "+
    " total = 0; "+
    " for (var i in values) { "+
        " total += values[i].count; "+
    " } "+
    " return {count:total} }";
	
	MapReduceCommand cmd = new MapReduceCommand(collection, map, reduce,
				     null, MapReduceCommand.OutputType.INLINE, null);
	
	MapReduceOutput out = collection.mapReduce(cmd);
	
	for (DBObject o : out.results()) {
		System.out.println(o.toString());
	}
	System.out.println("Done");
 

 
  }
}
    
