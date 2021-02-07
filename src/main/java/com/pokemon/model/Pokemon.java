package com.pokemon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Document(collection = "pokemo")
public class Pokemon {
	@Id
	String id;
	String name;
	String url;
	
Pokemon(String name,String url){
	this.name = name;
	this.url = url;
}

Pokemon(String id, String name,String url){
	this.name = name;
	this.url = url;
	this.id = id;
}

	
	/**
	 * 
	Integer base_experience;
	Integer height;
	boolean is_default;
	Integer order;
	Integer weight;
	String urlDetails;
	EvolvesFromSpecies evolves_from_species;
	EvolvesTo evolves_to;
	
	*/

	

}
