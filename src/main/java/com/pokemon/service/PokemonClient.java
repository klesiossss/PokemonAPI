package com.pokemon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;


import com.pokemon.model.Pokemon;
import com.pokemon.repository.PokemonRepository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Service
public class PokemonClient {
	
public static final String GET_ALL_POKEMON_API = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=50"; 
@Autowired
private PokemonRepository pokemonRepository;
static RestTemplate restTemplate = new RestTemplate();


public List<Pokemon> callGetAllPokemonAPI(){

	HttpHeaders headers = new HttpHeaders();
	headers. setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<String> entity = new HttpEntity<>("parameters",headers);
	ResponseEntity<String> result = restTemplate.exchange(GET_ALL_POKEMON_API, HttpMethod.GET, entity, String.class);
	System.out.println(result);
	String pok = result.getBody();  	
    List<Pokemon> listPokemon = splitAPI(pok);
    	
    return listPokemon;
}



public List<Pokemon> splitAPI(String pok) {
	
	String[] api = pok.split("[{\\,\\;\\:\\}]");

	String[] getId;
    String name = "";
    String url = "";
    String auxUrl = "";
    String auxName = "";
    String nextPage = "";
	int id = 0;
    ArrayList<Pokemon> listaPokemon = new ArrayList<Pokemon>();  
    for(int i= 11; i<api.length; i++) {
    	//System.out.println(api[i]);
    	auxName = api[i];
    	auxUrl = "https:"+api[i+3];
    	url = auxUrl.substring(0, auxUrl.length()-1);
    	name = auxName.substring(1, auxName.length()-1);
    	i = i+6; 	
        getId = StringUtils.split(url,"pokemon/");   
        id=  Integer.parseInt(getId[1].replaceAll("[\\D]", ""));
        
     	    
        listaPokemon.add(new Pokemon(id,name,url));       
    } 
    	return listaPokemon;
 }

}

