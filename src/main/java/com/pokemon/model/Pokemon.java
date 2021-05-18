package com.pokemon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String name;
	private String url;  
	private Integer base_experience;
	private Integer height;
	private Integer weight;
	
	
	
	
	

}
