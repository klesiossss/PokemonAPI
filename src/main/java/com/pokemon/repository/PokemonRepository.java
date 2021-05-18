package com.pokemon.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.model.Pokemon;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
	

	Page<Pokemon>findAll(Pageable pageable);	
	
	List<Pokemon> findByNameContainingIgnoreCase(String name); 
	Optional<Pokemon> findByNameIgnoreCase(String name);
	Optional<Pokemon> findByUrl(String url);
	
	List<Pokemon> findByWeightBetween(Integer weight1,Integer weight2);
	List<Pokemon> findByHeightBetween(Integer height1,Integer height2);
}
