package com.beerhouse.service.interfaces;

import com.beerhouse.dto.BeerDTO;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    List<BeerDTO> getAllBeers();

    BeerDTO createBeer(BeerDTO beerDTO);

    Optional<BeerDTO> getBeerById(Integer beerId);

    BeerDTO updateBeer(int beerId, BeerDTO beerDetails);

    Boolean deleteBeer(int beerId);

    BeerDTO partialUpdateBeer(BeerDTO beerDTO, int beerId);

}
