package com.pokemon;



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
	//pokemonRepository.deleteAll();

	 //	pokemonService.savePokemonFromAPI().forEach(System.out::println);
	
		System.out.println("\nWe've got Pokemons by their height and weight from DataBase!\n");
		//pokemonService.findByWeightAndHeightFromAPI(60,1000);
		//pokemonService.detailsPokemonbyId(400);
		//pokemonService.findAll().forEach(System.out::println);
		//System.out.println(pokemonService.findByExactName("bulbasaur"));
		//System.out.println(pokemonService.findById(202));
		//pokemonService.findPokemonByHeight(20).forEach(System.out::println);
		//pokemonService.findPokemonByWeight(100).forEach(System.out::println);
		
		
	}
}