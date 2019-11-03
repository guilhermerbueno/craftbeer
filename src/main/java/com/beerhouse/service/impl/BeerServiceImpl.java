package com.beerhouse.service.impl;

import com.beerhouse.dto.BeerDTO;
import com.beerhouse.mapper.BeerMapper;
import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.interfaces.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;
    private BeerMapper mapper = new BeerMapper();

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<BeerDTO> getAllBeers(){
        return mapper.map(beerRepository.findAll());
    }

    public BeerDTO createBeer(BeerDTO beer){
        return mapper.map(beerRepository.save(mapper.map(beer)));
    }

    private Optional<Beer> getBeer(Integer beerId){
        return beerRepository.findById(beerId);
    }

    public Optional<BeerDTO> getBeerById(Integer beerId) {
        return getBeer(beerId).map(mapper::map);
    }

    public BeerDTO updateBeer(int beerId, BeerDTO beerDetails){
        Beer beer = getBeer(beerId).orElse(null);
        if(beer == null){
            return null;
        }

        beer.setName(beerDetails.getName());
        beer.setAlcoholContent(beerDetails.getAlcoholContent());
        beer.setIngredients(beerDetails.getIngredients());
        beer.setPrice(beerDetails.getPrice());
        beer.setCategory(beerDetails.getCategory());

        return mapper.map(beerRepository.save(beer));
    }

    public Boolean deleteBeer(int beerId){
        Beer beer = getBeer(beerId).orElse(null);
        if(beer == null){
            return false;
        }

        beerRepository.deleteById(beerId);
        return true;
    }

    public BeerDTO partialUpdateBeer(BeerDTO beerDTO, int beerId){
        Beer beer = getBeer(beerId).orElse(null);
        if(beer == null){
            return null;
        }

        if(beerDTO.getName() != null){
            beer.setName(beerDTO.getName());
        }

        if(beerDTO.getAlcoholContent() != null){
            beer.setAlcoholContent(beerDTO.getAlcoholContent());
        }

        if(beerDTO.getIngredients() != null){
            beer.setIngredients(beerDTO.getIngredients());
        }

        if(beerDTO.getPrice() != null){
            beer.setPrice(beerDTO.getPrice());
        }

        if(beerDTO.getCategory() != null){
            beer.setCategory(beerDTO.getCategory());
        }

        return mapper.map(beerRepository.save(beer));
    }
}
