package com.pokemon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pokemon.model.Pokemon;
import com.pokemon.repository.PokemonRepository;
import com.pokemon.service.PokemonService;

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

		List<Pokemon> p2 = pokemonService.savePokemonFromAPI();
	
		System.out.println("\nWe've got Pokemons by their height and weight from DataBase!\n");
		List<Pokemon> p1 = pokemonService.findPokemonByWeightAndHeight(888);
		p1.forEach(System.out::println);
		
		 
  



	}
}