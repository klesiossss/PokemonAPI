package com.pokemon.controller;



import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public ResponseEntity <Page<Pokemon>>downloadAllPokemons(@PageableDefault(sort = "id", size = 20) Pageable pageable) {
		var pokemonPage = pokemonService.savePokemonFromAPI(pageable);
		return ResponseEntity.ok(pokemonPage);
	}
	
}
