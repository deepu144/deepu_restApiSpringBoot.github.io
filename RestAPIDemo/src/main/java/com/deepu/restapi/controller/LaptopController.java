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
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.service.LaptopService;

@RestController
@RequestMapping("/api")
public class LaptopController {
	
	@Autowired
	private LaptopService laptopService;
	
	@GetMapping("/laptops")
	public ResponseEntity<List<String>> getAllLaptop(@RequestParam(required = false) String processor){
		return laptopService.getAllLaptop(processor);
	}
	
	@PostMapping("/laptops")
	public ResponseEntity<Laptop> addLaptop(@RequestParam Integer id ,@RequestBody Laptop laptop){
		return laptopService.addLaptop(id,laptop);
	}
	
	@DeleteMapping("/laptops")
	public ResponseEntity<Laptop> deleteAllLaptop(){
		return laptopService.deleteAll();
	}
	
	@GetMapping("/laptops/{id}")
	public ResponseEntity<Laptop> getLaptop(@PathVariable int id){
		return laptopService.getLaptop(id);
	}
	
	@PutMapping("/laptops/{id}")
	public ResponseEntity<Laptop> updateLaptop(@PathVariable int id , @RequestBody Laptop laptop){
		return laptopService.updatelaptop(id,laptop);
	}
	
	@DeleteMapping("/laptops/{id}")
	public ResponseEntity<String> deleteLaptop(@PathVariable int id){
		return laptopService.deleteLaptop(id);
	}
	
}
