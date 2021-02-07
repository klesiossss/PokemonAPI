package com.pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pokemon.exception.DuplicatedResourceException;
import com.pokemon.exception.ResourceNotFoundException;
import com.pokemon.model.Pokemon;
import com.pokemon.model.PokemonDTO;
import com.pokemon.repository.PokemonRepository;

@Service
public class PokemonService {
	@Autowired
	private PokemonRepository pokemonRepository;
	private RestTemplate restTemplate = new RestTemplate();
	
	public Page<Pokemon> findAll(Pageable pageable){
		return	pokemonRepository.findAll(pageable);
	}
	
	
	public Pokemon findById(String id){
		
			Pokemon p1 = pokemonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());

		return p1;
	}
	
	public Page<Pokemon> findByExactName(String name, Pageable pageable){
		return pokemonRepository.findByNameIgnoreCase(name, pageable);		
	}
	
	public Page<Pokemon> findByName(String name, Pageable pageable){
		return pokemonRepository.findByNameContainingIgnoreCase(name, pageable);		
	}
	

	public Pokemon save(Pokemon pokemon){
		if(pokemon.getId() != null || pokemonRepository.findById(pokemon.getId()).isPresent()) throw new DuplicatedResourceException();
		return pokemonRepository.save(pokemon);
	}
	
	public Pokemon update(Pokemon pokemon){
		return pokemonRepository.save(pokemon);
	}
	
	public void delete(String id) {
		pokemonRepository.deleteById(id);
		
	}
	
	public List<Pokemon> savePokemonFromAPI() {
		try {
			var response =  restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?offset=0&limit=50", PokemonDTO.class);
			List<Pokemon> pok = response.getResults();
			return pok;
		} catch (Exception ex) {
			//ex.printStackTrace();
			return new ArrayList<Pokemon>();
			
		}		
	}

}
