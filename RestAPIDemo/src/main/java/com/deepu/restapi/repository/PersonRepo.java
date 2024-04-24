package com.deepu.restapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import com.deepu.restapi.model.Person;


//@RepositoryRestResource(collectionResourceRel = "person",path = "person")
@Repository
public interface PersonRepo extends JpaRepository<Person, Integer>{
	
	public List<Person> findByAge(int age);
	public List<Person> findByName(String name);

}
