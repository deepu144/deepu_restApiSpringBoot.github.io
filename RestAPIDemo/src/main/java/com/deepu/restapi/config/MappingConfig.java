package com.deepu.restapi.config;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deepu.restapi.dto.LaptopDto;
import com.deepu.restapi.dto.PersonDto;
import com.deepu.restapi.model.Laptop;
import com.deepu.restapi.model.Person;

@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
    	ModelMapper modelMapper = new ModelMapper();
    	configureMappings(modelMapper);
        return  modelMapper;
    }
    private void configureMappings(ModelMapper modelMapper) {
        TypeMap<Person, PersonDto> personToPersonDtoMap = modelMapper.createTypeMap(Person.class, PersonDto.class);
        personToPersonDtoMap.addMappings(mapper -> mapper.map(src -> mapLaptopsToNames(src.getLaptops()), PersonDto::setLaptops));
        modelMapper.createTypeMap(Laptop.class, LaptopDto.class)
                .addMapping(src -> src.getPerson().getName(), LaptopDto::setPerson);
    }
    
    private List<String> mapLaptopsToNames(List<Laptop> laptops) {
    	if(laptops==null) {
    		return Collections.emptyList();
    	}
        return laptops.stream().map(Laptop::getName).collect(Collectors.toList());
    }
}
