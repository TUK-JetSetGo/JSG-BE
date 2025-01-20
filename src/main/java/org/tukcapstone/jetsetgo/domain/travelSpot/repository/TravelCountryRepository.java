package org.tukcapstone.jetsetgo.domain.travelSpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCountry;

public interface TravelCountryRepository extends JpaRepository<TravelCountry, Long> {
}
