package com.deepu.restapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deepu.restapi.dto.LaptopDto;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;

@Component
public class DtoMapper {
	@Autowired
    private ModelMapper modelMapper;

    public PersonDto convertToDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    public LaptopDto convertToDto(Laptop laptop) {
        return modelMapper.map(laptop, LaptopDto.class);
    }
}
