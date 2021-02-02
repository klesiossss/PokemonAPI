package com.pokemon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pokemon.service.PokemonClient;
import com.pokemon.repository.PokemonRepository;
import com.pokemon.service.PokemonService;
import com.pokemon.model.Pokemon;

@SpringBootApplication
public class PokemonApplication implements CommandLineRunner {
	@Autowired 
	private PokemonRepository pokemonRepository;
	@Autowired
	private PokemonService pokemonService;
	@Autowired
	private PokemonClient pokemonClient;
	

public static void main(final String args[]) {
	SpringApplication.run(PokemonApplication.class, args);
}

@Override
public void run(String... strings) throws Exception {
	pokemonRepository.deleteAll();

	/**
	 *Get pokemons from API!   
	 */
System.out.println("We've got Pokemons from the API!");
List<Pokemon> listPokemon = pokemonClient.callGetAllPokemonAPI();

for(Pokemon lp: listPokemon){
	pokemonService.save(lp);
	System.out.println(lp);
}
/**
 *Find the past and future fases of a given pokemon id!  
 */
System.out.println("\nFind the past and future specie evolution of a given id pokemon!\n");
pokemonService.findById(2).forEach(System.out::println);



}
}