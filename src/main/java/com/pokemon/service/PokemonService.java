package com.pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pokemon.exception.DuplicatedResourceException;
import com.pokemon.exception.ResourceNotFoundException;
import com.pokemon.model.Pokemon;
import com.pokemon.repository.PokemonRepository;

@Service
public class PokemonService {
	@Autowired
	private PokemonRepository pokemonRepository;
	
	public Page<Pokemon> findAll(Pageable pageable){
		return	pokemonRepository.findAll(pageable);
	}
	
	
	public List<Pokemon> findById(int id){
		List<Pokemon> listPokemon = new ArrayList();
			if(id == 1) {
			Pokemon p1 =pokemonRepository.findById(id+1).orElseThrow(() -> new ResourceNotFoundException());
			Pokemon p2 =pokemonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
			listPokemon.add(p1);
			listPokemon.add(p2);
			return listPokemon;
			}
			Pokemon p1 =pokemonRepository.findById(id+1).orElseThrow(() -> new ResourceNotFoundException());
			Pokemon p2 =pokemonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
			Pokemon p3 =pokemonRepository.findById(id-1).orElseThrow(() -> new ResourceNotFoundException());
			listPokemon.add(p1);
			listPokemon.add(p2);
			listPokemon.add(p3);
		return listPokemon;
	}
	
	public Page<Pokemon> findByExactName(String name, Pageable pageable){
		return pokemonRepository.findByNameIgnoreCase(name, pageable);		
	}
	
	public Page<Pokemon> findByName(String name, Pageable pageable){
		return pokemonRepository.findByNameContainingIgnoreCase(name, pageable);		
	}
	

	public Pokemon save(Pokemon pokemon){
		if(pokemon.getId() == null || pokemonRepository.findById(pokemon.getId()).isPresent()) throw new DuplicatedResourceException();
		return pokemonRepository.save(pokemon);
	}
	
	public Pokemon update(Pokemon pokemon){
		return pokemonRepository.save(pokemon);
	}
	
	public void delete(int id) {
		pokemonRepository.deleteById(id);
		
	}

}
