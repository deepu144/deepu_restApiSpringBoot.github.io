package com.deepu.restapi.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.directory.InvalidAttributesException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deepu.restapi.dto.LaptopDto;
import com.deepu.restapi.mapper.DtoMapper;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;
import com.deepu.restapi.repository.LaptopRepo;
import com.deepu.restapi.repository.PersonRepo;
import com.deepu.restapi.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {
	@Autowired
	private LaptopRepo laptopRepo;
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private DtoMapper dtoMapper;

	public ResponseEntity<List<LaptopDto>> getAllLaptop(String processor) {
		if (processor == null) {
			List<LaptopDto> li = laptopRepo.findAll().stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
			return li.size() > 0 ? new ResponseEntity<List<LaptopDto>>(li, HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			List<LaptopDto> li = laptopRepo.findByProcessor(processor).stream().map(n -> dtoMapper.convertToDto(n))
					.collect(Collectors.toList());
			return new ResponseEntity<List<LaptopDto>>(li, HttpStatus.OK);
		}
	}

	public ResponseEntity<LaptopDto> addLaptop(Integer id, Laptop laptop) throws InvalidAttributesException{
		if(laptop.getName()==null || laptop.getProcessor()==null) {
			throw new InvalidAttributesException();
		}
		Optional<Person> checkPerson = personRepo.findById(id);
		if (checkPerson.isPresent()) {
			Laptop laptop2 = new Laptop();
			laptop2.setName(laptop.getName());
			laptop2.setProcessor(laptop.getProcessor());
			laptop2.setPerson(checkPerson.get());
			laptopRepo.save(laptop2);
			LaptopDto laptopDto = dtoMapper.convertToDto(laptop2);
			return new ResponseEntity<LaptopDto>(laptopDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<LaptopDto> deleteAll() {
		laptopRepo.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<LaptopDto> getLaptop(int id) {
		Optional<Laptop> checkLaptop = laptopRepo.findById(id);
		if (checkLaptop.isPresent()) {
			LaptopDto laptopDto = dtoMapper.convertToDto(checkLaptop.get());
			return new ResponseEntity<LaptopDto>(laptopDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<LaptopDto> updatelaptop(int id, Laptop laptop) throws InvalidAttributesException {
		if(laptop.getName()==null || laptop.getProcessor()==null) {
			throw new InvalidAttributesException();
		}
		Optional<Laptop> checkLaptop = laptopRepo.findById(id);
		if (checkLaptop.isPresent()) {
			Laptop laptop2 = checkLaptop.get();
			laptop2.setName(laptop.getName());
			laptop2.setProcessor(laptop.getProcessor());
			laptopRepo.save(laptop2);
			LaptopDto laptopDto = dtoMapper.convertToDto(laptop2);
			return new ResponseEntity<LaptopDto>(laptopDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<LaptopDto> deleteLaptop(int id) {
		Optional<Laptop> checkLaptop = laptopRepo.findById(id);
		if (checkLaptop.isPresent()) {
			LaptopDto laptopDto = dtoMapper.convertToDto(checkLaptop.get());
//			LaptopDto laptopDto = new LaptopDto();
			laptopRepo.delete(checkLaptop.get());
			return new ResponseEntity<LaptopDto>(laptopDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
