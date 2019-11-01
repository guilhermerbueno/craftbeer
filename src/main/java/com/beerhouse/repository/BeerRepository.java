package com.beerhouse.repository;

import com.beerhouse.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Beer repository
 *
 * @author Guilherme Rodrigues Bueno
 */
@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {

}
