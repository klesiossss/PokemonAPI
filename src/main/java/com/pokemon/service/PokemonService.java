package com.pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	
	public List<Pokemon>findPokemonByWeight(double weight){
		Integer weight1 =  (int)((1-0.1)*weight);
		Integer weight2 =  (int)((1+0.1)*weight);
		//double height1 = ((1-0.1)*height);
		//double height2 = (1+0.1)*height;
		return pokemonRepository.findByWeightBetween(weight1,weight2);
	}

	
	public List<Pokemon>findPokemonByHeight(double height){
		Integer height1 =  (int)((1-0.1)*height);
		Integer height2 =  (int)((1+0.1)*height);
	
		return pokemonRepository.findByHeightBetween(height1,height2);
	}
	
	
	
	public Optional<Pokemon> findById(Integer id){
		
		var pk = pokemonRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
		return Optional.of(pk);
	}
	
	public Pokemon findByExactName(String name){
		 var pokemon =  pokemonRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ResourceNotFoundException());
		 return pokemon;
	}
	
	public List<Pokemon> findByName(String name){
		return pokemonRepository.findByNameContainingIgnoreCase(name);		
	}
	

	public Pokemon save(Pokemon pokemon){	
		
		var podeSalvar = pokemon.getId() == null && !pokemonRepository.findByUrl(pokemon.getUrl()).isPresent();
			if(podeSalvar)
				return pokemonRepository.save(pokemon);	
			else
				throw new DuplicatedResourceException();
			
	}
	
	public Pokemon update(Pokemon pokemon){
		var podeUpdate = pokemon.getId() != null && !pokemonRepository.findById(pokemon.getId()).isEmpty();
		
		if(podeUpdate)
			return	 pokemonRepository.save(pokemon);
		else
			throw new ResourceNotFoundException();
		
	}
	
	public void delete(Integer id) {
		pokemonRepository.deleteById(id);
		
	}
	
	public List<Pokemon> savePokemonFromAPI() {
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
				
			System.out.println(pl.get(i)+"\n");
			}	
			
			return pl;
		} catch (Exception ex) {	
			
			ex.printStackTrace();				
		}
		return null;		
	}
	
	public Pokemon findPokemonbyIdFromAPI(Integer id){
		
			String url1 = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=900";
			String url2 = "https://pokeapi.co/api/v2/pokemon/"+id+"/";
			var response = restTemplate.getForObject(url1, PokemonDTO.class);
					
			List<Pokemon> pokemonList =  response.getResults().stream()
					.filter(p -> p.getUrl().equals(url2))
					.collect(Collectors.toList());
			
			var pk  = restTemplate.getForObject(pokemonList.get(0).getUrl(), Pokemon.class);
			pk.setUrl(pokemonList.get(0).getUrl());
			if (!pokemonRepository.findByNameIgnoreCase(pk.getName()).isPresent());
				save(pk);
				return pk;
					

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
	
	
	public List<Pokemon> findByWeightAndHeightFromAPI(Integer weight, Integer height) {
		try {
			
			var response =  restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?offset=0&limit=100", PokemonDTO.class);
			List<Pokemon> pok = response.getResults();	
	
			List<Pokemon> pl = new ArrayList<Pokemon>();
			for(int i=0; i< pok.size(); i++) {				
				Pokemon p = restTemplate.getForObject(pok.get(i).getUrl(),Pokemon.class);
				
				if (p.getWeight() >= (1- 0.1)*weight && p.getWeight() <= (1+0.1)*weight ||
					p.getHeight() >= (1- 0.1)*height && p.getHeight() <= (1+0.1)*height) {
					p.setUrl(pok.get(i).getUrl());
					pl.add(p);
				if (!pokemonRepository.findByNameIgnoreCase(p.getName()).isPresent())
					save(p);
				//System.out.println(p); 
				}
			}	
			
		return pl;
		} catch (Exception ex) {	
			ex.printStackTrace();		
		}
		return null;
				
	}
	
	
	
	

}
