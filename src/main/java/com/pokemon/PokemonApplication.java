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
		//pokemonRepository.deleteAll();

		/**
	 	*Get  Pokemons from API!   
 		*/
		//System.out.println("\nWe've got Pokemons from the API!\n");
		//Pageable pageable =  PageRequest.of(0, 20, Sort.by("id").ascending()); 
		//Page<Pokemon> page = pokemonService.savePokemonFromAPI(pageable);
		//System.out.println(page);


		/**
		 *Get the past and future by a given Pokemon id...   
		 */
		//pokemonService.detailsPokemonbyId(2).forEach(System.out::println);
//pokemonService.findById(3);



	}
}