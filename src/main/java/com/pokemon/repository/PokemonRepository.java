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
public interface PokemonRepository extends MongoRepository<Pokemon, String>{
	

	Page<Pokemon>findAll(Pageable pageable);
	
	
	Optional<Pokemon> findById(String id);

	Page<Pokemon> findByNameContainingIgnoreCase(String name, Pageable pageable);
	Page<Pokemon> findByNameIgnoreCase(String name, Pageable pageable);
	
}
