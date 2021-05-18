package com.pokemon.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pokemon.model.Pokemon;
import com.pokemon.repository.PokemonRepository;
import com.pokemon.service.PokemonService;


@RestController
@RequestMapping("api/v2/pokemon")
public class PokemonController {
	@Autowired
	public  PokemonRepository pokemonRepository;
	@Autowired
	private PokemonService pokemonService;
	

	@GetMapping()
	public ResponseEntity<Page<Pokemon>> findAllPokemons(@PageableDefault(sort = "id", size = 20) Pageable pageable) {
		var pokemons = pokemonService.findAll(pageable);
		return ResponseEntity.ok(pokemons);
	}


	@GetMapping("/all")
	public ResponseEntity<List<Pokemon>> findAllPokemons() {
		var pokemons = pokemonService.findAll();
		return ResponseEntity.ok(pokemons);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pokemon>> findById(@PathVariable Integer id) {
		var pokemon = pokemonService.findById(id);
		return ResponseEntity.ok(pokemon);
	}
	
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Pokemon> findAllByExactName(@PathVariable String name) {
		var pokemon = pokemonService.findByExactName(name);
		return ResponseEntity.ok(pokemon);
	}
	

	@GetMapping("/filter/name/{name}")
	public ResponseEntity<List<Pokemon>> findAllBytName(@PathVariable String name) {
		var users = pokemonService.findByName(name);
		return ResponseEntity.ok(users);
	}
	

	@GetMapping("/weight/{weight}")
	public ResponseEntity<List<Pokemon>> findPokemonByWeight(@PathVariable Integer weight) {
		 var pokemonList = pokemonService.findPokemonByWeight(weight);
		return ResponseEntity.ok(pokemonList);
	}


	@GetMapping("/height/{height}")
	public ResponseEntity<List<Pokemon>> findPokemonByHeight(@PathVariable Integer height) {
		 var pokemonList = pokemonService.findPokemonByHeight(height);
		return ResponseEntity.ok(pokemonList);
	}
	

	@PostMapping
	public ResponseEntity<Pokemon> save(@RequestBody @Valid Pokemon pokemon) {
		var pokemonSaved = pokemonService.save(pokemon);
		var location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pokemonSaved.getId()).toUri();
		return ResponseEntity.created(location).body(pokemonSaved);
	}
	

	@PutMapping
	public ResponseEntity<Object> update(@RequestBody @Valid Pokemon pokemon) {
		var pokemonSaved = pokemonService.update(pokemon);
		return ResponseEntity.ok(pokemonSaved);
	}
	
	
	@DeleteMapping
	public ResponseEntity<Pokemon> delete(@RequestBody @Valid Pokemon pokemon) {
		pokemonRepository.delete(pokemon);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
