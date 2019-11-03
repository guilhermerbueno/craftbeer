package com.beerhouse.controller;

import com.beerhouse.dto.BeerDTO;
import com.beerhouse.service.interfaces.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerService beerService;

    private URI createLocation(String path, Object parameter){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(parameter)
                .toUri();
    }

    @GetMapping
    public List<BeerDTO> getAllBeers(){
        return beerService.getAllBeers();
    }

    @PostMapping
    public ResponseEntity<BeerDTO> createBeer(@Valid @RequestBody BeerDTO beerDTO){
        beerDTO = beerService.createBeer(beerDTO);

        return ResponseEntity.created(createLocation("/{id}", beerDTO.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable("id") Integer beerId) {
        BeerDTO beerDTO = beerService.getBeerById(beerId).orElse(null);
        if(beerDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(beerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable("id") int beerId, @Valid @RequestBody BeerDTO beerDetails){
        BeerDTO beerDTO = beerService.updateBeer(beerId, beerDetails);
        if(beerDTO == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<BeerDTO>(beerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BeerDTO> deleteBeer(@PathVariable("id") int beerId){
        Boolean deleted = beerService.deleteBeer(beerId);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BeerDTO> partialUpdateBeer(@RequestBody BeerDTO beerDTO, @PathVariable("id") int beerId){
        beerDTO = beerService.partialUpdateBeer(beerDTO, beerId);
        if(beerDTO == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<BeerDTO>(beerDTO, HttpStatus.OK);
    }
}
