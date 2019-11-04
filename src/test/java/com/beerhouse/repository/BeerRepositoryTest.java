package com.beerhouse.repository;

import com.beerhouse.model.Beer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerRepositoryTest {

    @Autowired
    private BeerRepository beerRepository;

    @Test
    public void givenBeer_whenGetAll_returnOneItem(){
        Beer beer = createBeer();

        beerRepository.save(beer);
        List<Beer> beers = beerRepository.findAll();

        assertEquals(1, beers.size());
    }

    @Test
    public void givenBeer_whenSave_thenGetOk(){
        Beer beer = createBeer();

        Beer response = beerRepository.save(beer);
        assertEquals("Brahma", response.getName());
        assertEquals("Pilsen", response.getCategory());
    }

    @Test
    public void givenBeer_whenUpdate_thenGetOk(){
        Beer beer = createBeer();

        Beer response = beerRepository.save(beer);
        response.setName("Skol");
        response.setCategory("Weiss");
        beerRepository.save(beer);
        Beer updated = beerRepository.findById(response.getId()).orElse(null);

        assertEquals("Skol", updated.getName());
        assertEquals("Weiss", updated.getCategory());
    }

    @Test
    public void givenBeer_whenDelete_thenGetOk(){
        Beer beer = createBeer();

        Beer response = beerRepository.save(beer);
        beerRepository.deleteById(response.getId());
        List<Beer> beers = beerRepository.findAll();

        assertEquals(0, beers.size());
    }

    private Beer createBeer(){
        Beer beer = new Beer();
        beer.setName("Brahma");
        beer.setAlcoholContent("4.5");
        beer.setCategory("Pilsen");
        beer.setIngredients("Malte, lupulo");
        beer.setPrice(3.50);
        return beer;
    }
}
