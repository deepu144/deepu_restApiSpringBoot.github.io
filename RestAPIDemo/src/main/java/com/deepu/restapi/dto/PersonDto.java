package com.deepu.restapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonDto {
	private String name;
	private Integer age;
	private List<String> laptops;
}
