package com.deepu.restapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Person;
import com.deepu.restapi.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@PostMapping("/persons")
	public ResponseEntity<PersonDto> addPerson(@RequestBody Person person){
		try {
			return personService.addPerson(person);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/persons")
	public ResponseEntity<List<PersonDto>> getAllPerson(@RequestParam(required = false) Integer age){
		try {
			return personService.getAllPerson(age);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/persons")
	public ResponseEntity<PersonDto> deleteAllPerson(){
		try {
			return personService.deleteAllPerson();			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/persons/{id}")
	public ResponseEntity<PersonDto> getPerson(@PathVariable Integer id){
		try {
			return personService.getPerson(id);			
		} catch (Exception e) {
//			System.out.println(e.printStackTrace());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/persons/{id}")
	public ResponseEntity<PersonDto> updatePerson(@PathVariable Integer id , @RequestBody Person person){
		try {
			return personService.updatePerson(id, person);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/persons/{id}")
	public ResponseEntity<PersonDto> deletePerson(@PathVariable Integer id){
		try {
			return personService.deletePerson(id);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/persons/{id}/laptops")
	public ResponseEntity<List<String>> findAllLaptop(@PathVariable Integer id){
		try {
			return personService.findAllLaptop(id);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
