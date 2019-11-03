package com.beerhouse.service;

import com.beerhouse.dto.BeerDTO;
import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public List<BeerDTO> getAllBeers(){
        return beerRepository.findAll();
    }

    public BeerDTO createBeer(BeerDTO beer){
        return beerRepository.save(beer);
    }

    public BeerDTO getBeerById(Integer beerId) {
        return beerRepository.findById(beerId);
    }

    public ResponseEntity<BeerDTO> updateBeer(int beerId, BeerDTO beerDetails){
        Beer beer = beerRepository.findOne(beerId);
        if(beer == null){
            return ResponseEntity.notFound().build();
        }

        beer.setName(beerDetails.getName());
        beer.setAlcoholContent(beerDetails.getAlcoholContent());
        beer.setIngredients(beerDetails.getIngredients());
        beer.setPrice(beerDetails.getPrice());
        beer.setCategory(beerDetails.getCategory());

        final Beer updatedBeer = beerRepository.save(beerDetails);
        return ResponseEntity.ok(updatedBeer);
    }

    public Map<String, Boolean> deleteBeer(int beerId){
        Beer beer = beerRepository.findOne(beerId);
        if(beer == null){
            throw new ResourceNotFoundException("Teste", null);
        }

        beerRepository.delete(beer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public ResponseEntity<BeerDTO> partialUpdateBeer(BeerDTO partialUpdate, int beerId){

        return ResponseEntity.ok().build();
    }
}
