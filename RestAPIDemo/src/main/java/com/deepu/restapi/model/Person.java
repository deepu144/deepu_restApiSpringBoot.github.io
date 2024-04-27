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
	private Integer id;
	private String name;
	private Integer age;
	@OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Laptop> laptops = new ArrayList<>();
}
