Spring Data Neo4j
=================

Simple example showing how to use Spring Data Neo4j.
- It generates a set of 100 persons and connect each of them "create relationships" to exactly 10 other persons from the same set.

Person.java: simple POJO with needed annotations @NodeEntity, @Indexed, @GraphId...etc
PersonRepository.java: the magic is here! an interface that works without implementation!!

- Alot of jars are needed pom.xml is not provided yet.
- Running this using eclipse may need some configuration for AspectJ.
Refer to  http://hmkcode.com/spring-data-neo4j-ii for more details.

-HMK
