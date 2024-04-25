package com.deepu.restapi.serviceImpl;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.naming.directory.InvalidAttributesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;
import com.deepu.restapi.repository.PersonRepo;
import com.deepu.restapi.service.PersonService;
import com.deepu.restapi.util.DtoMapper;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private DtoMapper dtoMapper;

	public ResponseEntity<PersonDto> addPerson(Person person) throws InvalidParameterException{
		if(person.getId()==null || person.getAge()==null || person.getName()!=null) {
			throw new InvalidParameterException();
		}
		PersonDto personDto = dtoMapper.convertToDto(person);
		personRepo.save(person);
		return new ResponseEntity<PersonDto>(personDto, HttpStatus.CREATED);
	}

	public ResponseEntity<List<PersonDto>> getAllPerson(Integer age) {
		if (age != null) {
			List<Person> li = personRepo.findByAge(age);
			if (li.size() > 0) {
				List<PersonDto> pdList = personRepo.findByAge(age).stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
				return new ResponseEntity<List<PersonDto>>(pdList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} else {
			List<PersonDto> pdList = personRepo.findAll().stream().map(n -> dtoMapper.convertToDto(n)).collect(Collectors.toList());
			return new ResponseEntity<List<PersonDto>>(pdList, HttpStatus.OK);
		}
	}

	public ResponseEntity<PersonDto> deleteAllPerson() {
		personRepo.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<PersonDto> getPerson(Integer id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			PersonDto personDto = dtoMapper.convertToDto(person.get());
			return new ResponseEntity<PersonDto>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<PersonDto> updatePerson(Integer id, Person person) throws InvalidAttributesException {
		if(person.getAge()==null || person.getName()==null) {
			throw new InvalidAttributesException();
		}
		Optional<Person> personTest = personRepo.findById(id);
		if (personTest.isPresent()) {
			Person obj = personRepo.findById(id).get();
			obj.setAge(person.getAge());
			obj.setName(person.getName());
			personRepo.save(obj);
			PersonDto personDto = dtoMapper.convertToDto(obj);
			return new ResponseEntity<PersonDto>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<PersonDto> deletePerson(Integer id) {
		Optional<Person> person = personRepo.findById(id);
		if (person.isPresent()) {
			PersonDto personDto = dtoMapper.convertToDto(person.get());
			personRepo.delete(person.get());
			return new ResponseEntity<>(personDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<String>> findAllLaptop(Integer id) {
		Optional<Person> p = personRepo.findById(id);
		if (p.isPresent()) {
			List<String> laptopNames = p.get().getLaptops().stream().map(Laptop::getName).collect(Collectors.toList());
			return new ResponseEntity<List<String>>(laptopNames, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
