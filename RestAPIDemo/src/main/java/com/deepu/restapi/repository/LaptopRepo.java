package com.deepu.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;

import java.util.List;


@Repository
public interface LaptopRepo extends JpaRepository<Laptop, Integer> {
	
	public List<Laptop> findByProcessor(String processor);
	public List<Laptop> findByPerson(Person person);
}
