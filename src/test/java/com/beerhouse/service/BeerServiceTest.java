package com.beerhouse.service;

import com.beerhouse.dto.BeerDTO;
import com.beerhouse.service.impl.BeerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BeerServiceTest {

    @Autowired
    private BeerServiceImpl beerService;

    @Test
    public void createBeerWithSuccess(){
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setName("Brahma");
        beerDTO.setAlcoholContent("4.5");
        beerDTO.setCategory("Pilsen");
        beerDTO.setIngredients("Malte, lupulo");
        beerDTO.setPrice(3.50);

        beerService.createBeer(beerDTO);

        Optional<BeerDTO> optional = beerService.getBeerById(1);
        assertTrue(optional.isPresent());
    }
}
