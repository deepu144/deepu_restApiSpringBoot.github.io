package com.deepu.restapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.deepu.restapi.model.Person;
import com.deepu.restapi.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@PostMapping("/persons")
	public ResponseEntity<Person> addPerson(@RequestBody Person person){
		return personService.addPerson(person);
	}
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPerson(@RequestParam(required = false) Integer age){
		return personService.getAllPerson(age);
	}
	
	@DeleteMapping("/persons")
	public ResponseEntity<String> deleteAllPerson(){
		return personService.deleteAllPerson();
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Integer id){
		return personService.getPerson(id);
	}
	
	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable Integer id , @RequestBody Person person){
		return personService.updatePerson(id, person);
	}
	
	@DeleteMapping("/persons/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Integer id){
		return personService.deletePerson(id);
	}
	
	@GetMapping("/persons/{id}/laptops")
	public ResponseEntity<List<String>> findAllLaptop(@PathVariable Integer id){
		return personService.findAllLaptop(id);
	}
	
}
