package com.pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.model.Pokemon;
import com.pokemon.service.PokemonService;


@RestController
@RequestMapping("/api/v1/pokemon/")
public class PokemonClientController {

	@Autowired
	private PokemonService pokemonService;
	
	@GetMapping("{id}")
	public ResponseEntity<List<Pokemon>> detailsPokemonbyId(@PathVariable Integer id) {
		 var pokemonList = pokemonService.detailsPokemonbyId(id);
		return ResponseEntity.ok(pokemonList);
	}
	
	
	@GetMapping("download")
	public ResponseEntity <List<Pokemon>>downloadAllPokemons() {
		var pokemonPage = pokemonService.savePokemonFromAPI();
		return ResponseEntity.ok(pokemonPage);
	}
	
	@GetMapping("filter/{weight}/{height}")
	public ResponseEntity<List<Pokemon>> findByWeightAndHeightFomAPI(@PathVariable Integer weight, @PathVariable Integer height ) {
		 var pokemonList = pokemonService.findByWeightAndHeightFromAPI(weight,height);
		return ResponseEntity.ok(pokemonList);
	}	
	
}
