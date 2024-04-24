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
import com.deepu.restapi.repository.LaptopRepo;
import com.deepu.restapi.repository.PersonRepo;

@Service
public class LaptopService {
	
	@Autowired
	private LaptopRepo laptopRepo;
	@Autowired
	private PersonRepo personRepo;
	
	public ResponseEntity<List<String>> getAllLaptop(String processor){
		try {
			if(processor==null) {
				List<String> li = laptopRepo.findAll().stream()
						.map(Laptop::getName)
						.collect(Collectors.toList());
				return li.size()>0 ? new ResponseEntity<List<String>>(li,HttpStatus.OK) : new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);				
			}else {
				List<String> li = laptopRepo.findByProcessor(processor).stream()
						.map(Laptop::getName)
						.collect(Collectors.toList());
				return new ResponseEntity<List<String>>(li,HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Laptop> addLaptop(Integer id,Laptop laptop){
		try {
			Optional<Person> checkPerson = personRepo.findById(id);
			if(checkPerson.isPresent()) {
				Laptop laptop2 = new Laptop();
				laptop2.setName(laptop.getName());
				laptop2.setProcessor(laptop.getProcessor());
				laptop2.setPerson(checkPerson.get());
				laptopRepo.save(laptop2);
				return new ResponseEntity<Laptop>(laptop2,HttpStatus.OK);
			}else {
				return new ResponseEntity<Laptop>(HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			return new ResponseEntity<Laptop>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Laptop> deleteAll(){
		try {
			laptopRepo.deleteAll();
			return new ResponseEntity<Laptop>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Laptop>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Laptop> getLaptop(int id) {
		Optional<Laptop> checkLaptop = laptopRepo.findById(id);
		if(checkLaptop.isPresent()) {
			return new ResponseEntity<Laptop>(checkLaptop.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Laptop>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Laptop> updatelaptop(int id, Laptop laptop) {
		try {
			Optional<Laptop> checkLaptop = laptopRepo.findById(id);
			if(checkLaptop.isPresent()) {
				Laptop laptop2 = checkLaptop.get();
				laptop2.setName(laptop.getName());
				laptop2.setProcessor(laptop.getProcessor());
				laptopRepo.save(laptop2);
				return new ResponseEntity<Laptop>(laptop2,HttpStatus.OK);
			}else {
				return new ResponseEntity<Laptop>(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity<Laptop>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> deleteLaptop(int id) {
		try {
			Optional<Laptop> checkLaptop = laptopRepo.findById(id);
			if(checkLaptop.isPresent()) {
				laptopRepo.delete(checkLaptop.get());
				return new ResponseEntity<>("DELETED",HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
