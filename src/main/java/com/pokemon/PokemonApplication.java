package com.pokemon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import com.pokemon.repository.PokemonRepository;
import com.pokemon.service.PokemonService;



import com.pokemon.model.Pokemon;

@SpringBootApplication
public class PokemonApplication implements CommandLineRunner {
	@Autowired 
	private PokemonRepository pokemonRepository;
	@Autowired
	private PokemonService pokemonService;
	

public static void main(final String args[]) {
	SpringApplication.run(PokemonApplication.class, args);
}

@Override
public void run(String... strings) throws Exception {
	pokemonRepository.deleteAll();

	/**
	 *Get  pokemons from API!   
	 */
System.out.println("\nWe've got Pokemons from the API!\n");

List<Pokemon> pokemons = pokemonService.savePokemonFromAPI();
pokemonRepository.saveAll(pokemons);

pokemons.forEach(System.out::println);

/**
 *Find pokemon by id!  
 */
//System.out.println("\nFind the past and future specie evolution of a given id pokemon!\n");
//System.out.println(pokemonService.findById("Put the id in here!"));



}
}