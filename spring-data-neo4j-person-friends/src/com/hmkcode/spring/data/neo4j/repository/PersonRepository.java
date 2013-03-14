package com.hmkcode.spring.data.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.hmkcode.spring.data.neo4j.entity.Person;;

public interface PersonRepository extends GraphRepository<Person> {}
