package com.beerhouse.service;

import com.beerhouse.dto.BeerDTO;
import com.beerhouse.service.impl.BeerServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BeerServiceTest {

    @Autowired
    private BeerServiceImpl beerService;

    private static BeerDTO beerDTO;

    @BeforeClass
    public static void builderBeer(){
        beerDTO = new BeerDTO();
        beerDTO.setName("Brahma");
        beerDTO.setAlcoholContent("4.5");
        beerDTO.setCategory("Pilsen");
        beerDTO.setIngredients("Malte, lupulo");
        beerDTO.setPrice(3.50);
    }

    @Test
    public void givenBeer_whenCreate_thenGetOk(){
        beerService.createBeer(beerDTO);

        Optional<BeerDTO> optional = beerService.getBeerById(1);
        assertTrue(optional.isPresent());
    }

    @Test
    public void givenBeer_whenGetById_thenOk(){
        BeerDTO response = beerService.createBeer(beerDTO);
        BeerDTO founded = beerService.getBeerById(response.getId()).orElse(null);

        assertEquals("Brahma", founded.getName());
        assertEquals("Pilsen", founded.getCategory());
    }

    @Test
    public void givenBeer_whenGetByNonexistentId_thenFail(){
        BeerDTO response = beerService.createBeer(beerDTO);
        BeerDTO founded = beerService.getBeerById(100).orElse(null);

        assertEquals(null, founded);
    }

    @Test
    public void givenBeer_whenUpdate_thenOk(){
        BeerDTO response = beerService.createBeer(beerDTO);
        response.setName("Skol");
        response.setCategory("Weiss");

        BeerDTO updated = beerService.updateBeer(response.getId(), response);

        assertEquals("Skol", updated.getName());
        assertEquals("Weiss", updated.getCategory());
    }

    @Test
    public void givenBeer_whenUpdateNonexistent_thenFail(){
        BeerDTO response = beerService.createBeer(beerDTO);
        response.setName("Skol");
        response.setCategory("Weiss");

        BeerDTO updated = beerService.updateBeer(100, response);

        assertEquals(null, updated);
    }

    @Test
    public void givenBeer_whenDelete_thenOk(){
        BeerDTO response = beerService.createBeer(beerDTO);
        Boolean deleted = beerService.deleteBeer(response.getId());

        assertEquals(true, deleted);
    }


    @Test
    public void givenBeer_whenDeleteNonexistent_thenFail(){
        BeerDTO response = beerService.createBeer(beerDTO);
        Boolean deleted = beerService.deleteBeer(100);

        assertEquals(false, deleted);
    }

    @Test
    public void givenBeer_whenUpdatePartial_thenOk(){
        BeerDTO response = beerService.createBeer(beerDTO);
        response.setName("Skol");
        response.setCategory("Weiss");

        BeerDTO updated = beerService.partialUpdateBeer(response, response.getId());

        assertEquals("Skol", updated.getName());
        assertEquals("Weiss", updated.getCategory());
    }
}
