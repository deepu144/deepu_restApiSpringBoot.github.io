package com.deepu.restapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Person {
	
	@Id
	private int id;
	private String name;
	private int age;
	@OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Laptop> laptops = new ArrayList<>();
	
}
