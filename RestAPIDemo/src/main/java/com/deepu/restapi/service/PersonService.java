package com.deepu.restapi.service;

import java.util.List;
import javax.naming.directory.InvalidAttributesException;
import org.springframework.http.ResponseEntity;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Person;

public interface PersonService {
	public ResponseEntity<PersonDto> addPerson(Person person);
	public ResponseEntity<List<PersonDto>> getAllPerson(Integer age);
	public ResponseEntity<PersonDto> deleteAllPerson();
	public ResponseEntity<PersonDto> getPerson(Integer id);
	public ResponseEntity<PersonDto> updatePerson(Integer id , Person person) throws InvalidAttributesException;
	public ResponseEntity<PersonDto> deletePerson(Integer id);
	public ResponseEntity<List<String>> findAllLaptop(Integer id);
}
