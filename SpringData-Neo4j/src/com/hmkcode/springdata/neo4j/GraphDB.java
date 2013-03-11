package com.hmkcode.springdata.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.hmkcode.vo.*;


public class GraphDB {

	 @Autowired         
	 private Neo4jTemplate template; 
	 
	 public void buildDataModel(){
		registerShutdownHook( template.getGraphDatabaseService());
		Transaction tx = template.getGraphDatabase().beginTx();
		try{
			Person p1 = new Person();
			p1.setId("person-01");
			p1.setName("John");
			Person p2 = new Person();
			p2.setId("person-02");
			p2.setName("Brit");
			Person p3 = new Person();
			p3.setId("person-03");
			p3.setName("Adam");
			
			p2.addFriend(p3);
			p1.addFriend(p2);
			
			Person savedPerson = template.save(p1);
			 
			Person loadedPerson = template.findOne(savedPerson.getNodeId(), Person.class);
			
			System.out.println("User: "+loadedPerson.getNodeId());
			tx.success();
			tx.finish();
		}finally{
			 System.out.println("finally");
		 }
	 }

	public Neo4jTemplate getTemplate() {
		return template;
	}

	public void setTemplate(Neo4jTemplate template) {
		this.template = template;
	}
	private static void registerShutdownHook( final GraphDatabaseService graphDb )
	{
	    Runtime.getRuntime().addShutdownHook( new Thread()
	    {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	           
	        }
	    } );
	}
	 
	
}
