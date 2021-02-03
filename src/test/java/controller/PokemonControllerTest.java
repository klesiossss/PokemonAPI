package controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.pokemon.model.Pokemon;
import com.pokemon.service.PokemonService;
import com.pokemon.controller.PokemonController;
import com.pokemon.exception.ResourceNotFoundException;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest  {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PokemonService pokemonService;
	
	private static ObjectMapper mapper;
	
	private static Pokemon pokemon;
	
	
	@BeforeAll
	static void beforeAll() {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				
		
		
		pokemon = new Pokemon();
		pokemon.setId(3);
		pokemon.setName("Lorem Ipsum");
		pokemon.setUrl("https://boudasdf.co/v3/sea/2");
		
		
	}

	@Test
	@DisplayName("Deve retornar 200 ao buscar um Pokemon pelo id cadastrado")
	void testObterPorId() throws Exception {
		List<Pokemon> listPok = null;
		listPok.add(pokemon);
		when(pokemonService.findById(3)).thenReturn(listPok);
		
		mockMvc.perform(get("api/v1/pokemon/3")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(pokemon)));
	}
	
	@Test
	@DisplayName("Deve retornar 404 ao buscar um Pokemon pelo id inexistente")
	void testNaoObterPorId() throws Exception {
		when(pokemonService.findById(any(Integer.class))).thenThrow(ResourceNotFoundException.class);
		
		mockMvc.perform(get("api/v1/pokemon/3")
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Deve retornar 201 ao salvar um Pokemon")
	void testSalvar() throws Exception {
		when(pokemonService.save(any(Pokemon.class))).thenReturn(pokemon);
		
		var novoPokemon = new Pokemon();
		novoPokemon.setId(5);
		novoPokemon.setName("yadyasoure");
		novoPokemon.setUrl("https://sasea/v4.co/sesaa/5");
		
		
		mockMvc.perform(post("api/v1/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(novoPokemon)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(pokemon)))
                .andExpect(header().string("Location", "http://localhost/api/v1/pokemon/5"));
	}
	
	@Test
	@DisplayName("Deve retornar 400 ao tentar salvar um Pokemon inválido")
	void testNaoSalvar() throws Exception {
		var novoPokemon = new Pokemon();
		novoPokemon.setId(5);
		novoPokemon.setName("yadyasoure");
		novoPokemon.setUrl("https://sasea/v4.co/sesaa/5");
		
		mockMvc.perform(post("api/v1/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(novoPokemon)))
                .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Deve retornar 200 ao editar os dados de um pokemon")
	void testEditar() throws Exception {
		when(pokemonService.update(any(Pokemon.class))).thenReturn(pokemon);
		
		mockMvc.perform(put("api/v1/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pokemon)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(pokemon)));
	}

	@Test
	@DisplayName("Deve retornar 200 ao remover um Pokemon da base de dados")
	void testRemover() throws Exception {
		doNothing().when(pokemonService).delete(any(Integer.class));
		
        mockMvc.perform(delete("api/v1/pokemon")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsString(Integer.class)))
                .andExpect(status().isOk());
	}

}