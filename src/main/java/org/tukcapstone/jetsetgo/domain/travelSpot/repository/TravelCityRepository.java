package org.tukcapstone.jetsetgo.domain.travelSpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCountry;

import java.util.List;

public interface TravelCityRepository extends JpaRepository<TravelCity, Long> {
    List<TravelCity> findByTravelCountry(TravelCountry travelCountry);
}
