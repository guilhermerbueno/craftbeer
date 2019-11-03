package com.beerhouse.controller;

import com.beerhouse.dto.BeerDTO;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class BeerControllerTest {

    final String BASE_PATH = "http://localhost:9000/beers";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void createBeerWithSuccess() throws URISyntaxException {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setName("Brahma");
        beerDTO.setAlcoholContent("4.5");
        beerDTO.setCategory("Pilsen");
        beerDTO.setIngredients("Malte, lupulo");
        beerDTO.setPrice(3.50);

        URI uri = new URI(BASE_PATH);

        ResponseEntity<BeerDTO> response = restTemplate.postForEntity(uri, beerDTO, BeerDTO.class);
        assertEquals(201, response.getStatusCode().value());
        assertEquals("Brahma", response.getBody().getName());
        assertEquals("Pilsen", response.getBody().getCategory());
    }
}
