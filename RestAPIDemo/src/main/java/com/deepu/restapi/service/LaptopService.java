package com.deepu.restapi.service;

import java.util.List;

import javax.naming.directory.InvalidAttributesException;
import org.springframework.http.ResponseEntity;
import com.deepu.restapi.dto.LaptopDto;
import com.deepu.restapi.model.Laptop;

public interface LaptopService {
	public ResponseEntity<List<LaptopDto>> getAllLaptop(String processor);
	public ResponseEntity<LaptopDto> addLaptop(Integer id,Laptop laptop) throws InvalidAttributesException;
	public ResponseEntity<LaptopDto> deleteAll();
	public ResponseEntity<LaptopDto> getLaptop(int id);
	public ResponseEntity<LaptopDto> updatelaptop(int id, Laptop laptop) throws InvalidAttributesException;
	public ResponseEntity<LaptopDto> deleteLaptop(int id);
}
