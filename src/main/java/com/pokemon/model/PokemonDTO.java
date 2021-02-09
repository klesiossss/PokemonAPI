package com.pokemon.model;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PokemonDTO {
	private List<Pokemon> results;
	private String next;
	private String previous;
	
}