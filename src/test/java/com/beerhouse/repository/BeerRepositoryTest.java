package com.beerhouse.repository;

import com.beerhouse.model.Beer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerRepositoryTest {

    @Autowired
    private BeerRepository beerRepository;

    @Test
    public void save(){
        Beer beer = new Beer();
        beer.setName("Brahma");
        beer.setAlcoholContent("4.5");
        beer.setCategory("Pilsen");
        beer.setIngredients("Malte, lupulo");
        beer.setPrice(3.50);

        Beer response = beerRepository.save(beer);
        assertEquals("Brahma", response.getName());
        assertEquals("Pilsen", response.getCategory());
    }
}
