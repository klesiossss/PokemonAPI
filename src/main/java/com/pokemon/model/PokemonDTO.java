package com.pokemon.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDTO {
	
	private List<Pokemon> results;
	private String next;
	private String previous;
	
}