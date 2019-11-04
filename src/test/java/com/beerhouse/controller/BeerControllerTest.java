package com.beerhouse.controller;

import com.beerhouse.dto.BeerDTO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerControllerTest {

    @LocalServerPort
    int randomServerPort;

    final static String BASE_PATH = "http://localhost:";

    private RestTemplate restTemplate = new RestTemplate();

    private static BeerDTO beerDTO;

    @BeforeClass
    public static void createBeer() throws URISyntaxException {
        beerDTO = new BeerDTO();
        beerDTO.setName("Brahma");
        beerDTO.setAlcoholContent("4.5");
        beerDTO.setCategory("Pilsen");
        beerDTO.setIngredients("Malte, lupulo");
        beerDTO.setPrice(3.50);
    }

    @Test
    public void givenBeer_whenGetAll_thenSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/beers";
        URI uri = new URI(baseUrl);

        restTemplate.postForEntity(uri, beerDTO, BeerDTO.class);

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotEquals("", response.getBody());
    }

    @Test
    public void givenBeer_whenCreate_thenSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/beers";
        URI uri = new URI(baseUrl);

        ResponseEntity<BeerDTO> response = restTemplate.postForEntity(uri, beerDTO, BeerDTO.class);

        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void givenBeer_whenDelete_thenSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/beers";
        URI uri = new URI(baseUrl);

        ResponseEntity<BeerDTO> response = restTemplate.postForEntity(uri, beerDTO, BeerDTO.class);

        restTemplate.delete(response.getHeaders().get("Location").get(0), String.class);
    }

    @Test(expected = HttpClientErrorException.class)
    public void givenBeer_whenDeleteAndTryGetById_thenFail() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/beers";
        URI uri = new URI(baseUrl);

        ResponseEntity<BeerDTO> response = restTemplate.postForEntity(uri, beerDTO, BeerDTO.class);

        restTemplate.delete(response.getHeaders().get("Location").get(0), String.class);
        restTemplate.getForEntity(response.getHeaders().get("Location").get(0), String.class);
    }

    @Test
    public void givenBeer_whenUpdate_thenSuccess() throws URISyntaxException {
        String baseUrl = "http://localhost:" + randomServerPort + "/beers";
        URI uri = new URI(baseUrl);
        ResponseEntity<BeerDTO> response = restTemplate.postForEntity(uri, beerDTO, BeerDTO.class);

        HttpEntity<BeerDTO> entity = new HttpEntity<>(beerDTO);
        baseUrl = response.getHeaders().get("Location").get(0);
        uri = new URI(baseUrl);
        ResponseEntity<BeerDTO> updated = restTemplate.exchange(uri, HttpMethod.PUT, entity, BeerDTO.class);

        assertEquals(HttpStatus.OK.value(), updated.getStatusCode().value());
    }
}
