package com.deepu.restapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;
import com.deepu.restapi.repository.PersonRepo;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepo personRepo;
	
	public ResponseEntity<Person> addPerson(Person person){
		try {
			personRepo.save(person);
			return new ResponseEntity<Person>(person,HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Person>> getAllPerson(Integer age){
		try {
			if(age!=null) {
				List<Person> li = personRepo.findByAge(age);
				if(li.size()>0) {
					return new ResponseEntity<List<Person>>(personRepo.findByAge(age),HttpStatus.OK);					
				}else {
					return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
				}
			}else {
				return new ResponseEntity<List<Person>>(personRepo.findAll(),HttpStatus.OK);				
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
	}
	
	public ResponseEntity<String> deleteAllPerson(){
		try {
			personRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Person> getPerson(Integer id){
		try {
			Optional<Person> person = personRepo.findById(id);
			if(person.isPresent()) {
				return new ResponseEntity<Person>(person.get(),HttpStatus.OK);
			}else {
				return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Person> updatePerson(Integer id , Person person){
		try {
			Optional<Person> personTest = personRepo.findById(id);
			if(personTest.isPresent()) {
				Person obj = personRepo.findById(id).get();
				obj.setAge(person.getAge());
				obj.setName(person.getName());
				personRepo.save(obj);
				return new ResponseEntity<Person>(obj,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> deletePerson(Integer id){
		try {
			Optional<Person> person = personRepo.findById(id);
			if(person.isPresent()) {
				personRepo.delete(person.get());
				return new ResponseEntity<>("DELETED SUCCESSFULLY",HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<String>> findAllLaptop(Integer id) {
		try {
			Optional<Person> p = personRepo.findById(id);
			if(p.isPresent()) {
				List<String> laptopNames = p.get().getLaptops().stream()
	                    .map(Laptop::getName)
	                    .collect(Collectors.toList());
	            return new ResponseEntity<>(laptopNames, HttpStatus.OK);		
			}else {
				return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
