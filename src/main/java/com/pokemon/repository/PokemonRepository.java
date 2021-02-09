package com.pokemon.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pokemon.model.Pokemon;


@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, Integer>{
	

	Page<Pokemon>findAll(Pageable pageable);
	
	
	Optional<Pokemon> findById(Integer id);

	List<Pokemon> findByNameContainingIgnoreCase(String name);
	Optional<Pokemon> findByNameIgnoreCase(String name);
	
}
