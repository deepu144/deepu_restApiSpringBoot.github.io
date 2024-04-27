package com.deepu.restapi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.deepu.restapi.dto.LaptopDto;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;

@Component
public class DtoMapper {
    public PersonDto convertToDto(Person person) {
    	PersonDto personDto = new PersonDto();
    	personDto.setName(person.getName());
    	personDto.setAge(person.getAge());
    	List<Laptop> li = person.getLaptops();
    	if(li.size()==0) {
    		List<String> li2 = new ArrayList<>();
    		personDto.setLaptops(li2);
    	}else {
    		List<String> li3 = person.getLaptops().stream().map(Laptop::getName).collect(Collectors.toList());
    		personDto.setLaptops(li3);
    	}
        return personDto;
    }

    public LaptopDto convertToDto(Laptop laptop) {
        LaptopDto laptopDto = new LaptopDto();
        laptopDto.setName(laptop.getName());
        laptopDto.setProcessor(laptop.getProcessor());
        laptopDto.setPerson(laptop.getPerson().getName());
        return laptopDto;
    }
}
