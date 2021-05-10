package com.pokemon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Pokemon {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pokemonId == null) ? 0 : pokemonId.hashCode());
		result = prime * result + ((base_experience == null) ? 0 : base_experience.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isDefault ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderPokemon == null) ? 0 : orderPokemon.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (pokemonId == null) {
			if (other.pokemonId != null)
				return false;
		} else if (!pokemonId.equals(other.pokemonId))
			return false;
		if (base_experience == null) {
			if (other.base_experience != null)
				return false;
		} else if (!base_experience.equals(other.base_experience))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDefault != other.isDefault)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderPokemon == null) {
			if (other.orderPokemon != null)
				return false;
		} else if (!orderPokemon.equals(other.orderPokemon))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String pokemonId;
	
	public Pokemon() {
		
	}
	
	public Pokemon(Integer id, String pokemonId, String name, String url, Integer base_experience, Integer height,
			boolean isDefault, Integer orderPokemon, Integer weight) {
		super();
		this.id = id;
		this.pokemonId = pokemonId;
		this.name = name;
		this.url = url;
		this.base_experience = base_experience;
		this.height = height;
		this.isDefault = isDefault;
		this.orderPokemon = orderPokemon;
		this.weight = weight;
	}
	public String getpokemonId() {
		return pokemonId;
	}
	public void setpokemonId(String pokemonId) {
		this.pokemonId = pokemonId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getBase_experience() {
		return base_experience;
	}
	public void setBase_experience(Integer base_experience) {
		this.base_experience = base_experience;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public boolean isisDefault() {
		return isDefault;
	}
	public void setisDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getorderPokemon() {
		return orderPokemon;
	}
	public void setorderPokemon(Integer orderPokemon) {
		this.orderPokemon = orderPokemon;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	private String name;
	private String url;  
	private Integer base_experience;
	private Integer height;
	private boolean isDefault;
	private Integer orderPokemon;
	private Integer weight;

}
