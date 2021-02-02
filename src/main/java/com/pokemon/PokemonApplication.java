package com.pokemon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pokemon.service.PokemonClient;
import com.pokemon.repository.PokemonRepository;
import com.pokemon.model.Pokemon;

@SpringBootApplication
public class PokemonApplication implements CommandLineRunner {
	@Autowired 
	private static PokemonRepository pokemonRepository;
	@Autowired
	private PokemonClient pokemonClient;

public static void main(final String args[]) {
	SpringApplication.run(PokemonApplication.class, args);
}

@Override
public void run(String... strings) throws Exception {



System.out.println("Find all");
List<Pokemon> listPokemon = pokemonClient.callGetAllPokemonAPI();

for(Pokemon lp: listPokemon){
	pokemonClient.save(lp);
	System.out.println(lp);
}

//System.out.println("Find by findByCPF");
//colaboradorRepository.findByCpf("04568423309").forEach(System.out::println);


}
}