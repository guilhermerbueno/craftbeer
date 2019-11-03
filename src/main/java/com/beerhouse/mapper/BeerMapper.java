package com.beerhouse.mapper;

import com.beerhouse.dto.BeerDTO;
import com.beerhouse.model.Beer;

import java.util.ArrayList;
import java.util.List;

public class BeerMapper {

    public BeerDTO map(Beer beer){
        if(beer != null){
            return new BeerDTO(beer.getId(),beer.getName(),beer.getIngredients(),beer.getAlcoholContent(),beer.getPrice(),beer.getCategory());
        }
        return null;
    }

    public Beer map(BeerDTO beer){
        if(beer != null){
            return new Beer(beer.getId(),beer.getName(),beer.getIngredients(),beer.getAlcoholContent(),beer.getPrice(),beer.getCategory());
        }
        return null;
    }

    public List<BeerDTO> map(List<Beer> beers){
        List<BeerDTO> beersList = new ArrayList<>();
        for(Beer beer : beers){
            beersList.add(new BeerDTO(beer.getId(),beer.getName(),beer.getIngredients(),beer.getAlcoholContent(),beer.getPrice(),beer.getCategory()));
        }
        return beersList;
    }
}
