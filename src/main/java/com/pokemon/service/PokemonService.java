package com.pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	
	public List<Pokemon> findAll(){
		return	pokemonRepository.findAll(Sort.by("id"));
	}

	public Page<Pokemon> findAll(Pageable pageable){
		return	pokemonRepository.findAll(pageable);
	}
	
	
	public Pokemon findById(Integer id){
		
		var pk = pokemonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return pk;
	}
	
	public Pokemon findByExactName(String name){
		 var pokemon =  pokemonRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ResourceNotFoundException());
		 return pokemon;
	}
	
	public List<Pokemon> findByName(String name){
		return pokemonRepository.findByNameContainingIgnoreCase(name);		
	}
	

	public Pokemon save(Pokemon pokemon){	
		try {
			if(pokemonRepository.findByNameIgnoreCase(pokemon.getName()).isPresent()) throw new DuplicatedResourceException();
			return pokemonRepository.save(pokemon);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new Pokemon();
			
		}
			
	}
	
	public Pokemon update(Pokemon pokemon){
		if(pokemonRepository.findById(pokemon.getId()).isEmpty()) throw new ResourceNotFoundException();
		return pokemonRepository.save(pokemon);
	}
	
	public void delete(Integer id) {
		pokemonRepository.deleteById(id);
		
	}
	
	public Page<Pokemon> savePokemonFromAPI(Pageable pageable) {
		try {
			
			var response =  restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?offset=0&limit=100", PokemonDTO.class);
			List<Pokemon> pok = response.getResults();	
	
			List<Pokemon> pl = new ArrayList<Pokemon>();
			for(int i=0; i< pok.size(); i++) {				
				Pokemon p = restTemplate.getForObject(pok.get(i).getUrl(),Pokemon.class);
				p.setUrl(pok.get(i).getUrl());
				pl.add(p);
				if (!pokemonRepository.findByNameIgnoreCase(pl.get(i).getName()).isPresent())
					save(pl.get(i));
				
			System.out.println(pl.get(i));
			}	
			
			int start = (int) pageable.getOffset();
			int end = (start + pageable.getPageSize()) > pl.size() ? pl.size() : (start + pageable.getPageSize());
			Page<Pokemon> pages = new PageImpl<Pokemon>(pl.subList(start, end), pageable, pl.size());
			return pages;
		} catch (Exception ex) {	
			
			ex.printStackTrace();				
		}
		return null;		
	}
	
	public Pokemon findPokemonbyIdFromAPI(Integer id){
		try {
			
			String url1 = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=900";
			String url2 = "https://pokeapi.co/api/v2/pokemon/"+id+"/";
			var response = restTemplate.getForObject(url1, PokemonDTO.class);
					
			List<Pokemon> pokemonList =  response.getResults().stream()
					.filter(p -> p.getUrl().equals(url2))
					.collect(Collectors.toList());
			
			var pk  = restTemplate.getForObject(pokemonList.get(0).getUrl(), Pokemon.class);
			pk.setUrl(pokemonList.get(0).getUrl());
			if (!pokemonRepository.findByNameIgnoreCase(pk.getName()).isPresent())
				save(pk);
				
			
			return pk;
					
		} catch(Exception ex) {
			//ex.printStackTrace();
			return new Pokemon();
			
		}
		
	}
	
	
	public List<Pokemon> detailsPokemonbyId(Integer id){
		List<Pokemon> pokemons = new ArrayList<Pokemon>();
		if (id ==1) {
			pokemons.add(findPokemonbyIdFromAPI(id));
			pokemons.add(findPokemonbyIdFromAPI(id+1));
			return pokemons;
		}else if (id ==1118) {
			pokemons.add(findPokemonbyIdFromAPI(id));
			pokemons.add(findPokemonbyIdFromAPI(id-1));	
			return pokemons;
		}else if (id > 1 && id < 1118) {
			pokemons.add(findPokemonbyIdFromAPI(id));
			pokemons.add(findPokemonbyIdFromAPI(id+1));
			pokemons.add(findPokemonbyIdFromAPI(id-1));
			return pokemons;
		}else {
			throw new ResourceNotFoundException();
			
		}
			
	}
	
	

}
