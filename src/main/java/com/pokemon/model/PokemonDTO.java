package com.pokemon.model;

import java.util.List;


public class PokemonDTO {
	
	public PokemonDTO() {
		
	}
	public List<Pokemon> getResults() {
		return results;
	}
	public void setResults(List<Pokemon> results) {
		this.results = results;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	private List<Pokemon> results;
	public PokemonDTO(List<Pokemon> results, String next, String previous) {
		super();
		this.results = results;
		this.next = next;
		this.previous = previous;
	}
	private String next;
	private String previous;
	
}