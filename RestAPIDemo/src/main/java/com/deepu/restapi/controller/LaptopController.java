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

import com.deepu.restapi.dto.LaptopDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.service.LaptopService;

@RestController
@RequestMapping("/api")
public class LaptopController {
	@Autowired
	private LaptopService laptopService;
	
	@GetMapping("/laptops")
	public ResponseEntity<List<LaptopDto>> getAllLaptop(@RequestParam(required = false) String processor){
		try {
			return laptopService.getAllLaptop(processor);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/laptops")
	public ResponseEntity<LaptopDto> addLaptop(@RequestParam Integer id ,@RequestBody Laptop laptop){
		try {
			return laptopService.addLaptop(id,laptop);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/laptops")
	public ResponseEntity<LaptopDto> deleteAllLaptop(){
		try {
			return laptopService.deleteAll();			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/laptops/{id}")
	public ResponseEntity<LaptopDto> getLaptop(@PathVariable int id){
		try {
			return laptopService.getLaptop(id);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/laptops/{id}")
	public ResponseEntity<LaptopDto> updateLaptop(@PathVariable int id , @RequestBody Laptop laptop){
		try {
			return laptopService.updatelaptop(id,laptop);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/laptops/{id}")
	public ResponseEntity<LaptopDto> deleteLaptop(@PathVariable int id){
		try {
			return laptopService.deleteLaptop(id);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
