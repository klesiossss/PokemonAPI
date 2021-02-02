package com.pokemon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "pokemon")
public class Pokemon {
	@Id
	Integer id;
	String name;
	String url;
	
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
