package org.tukcapstone.jetsetgo.domain.touristSpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

public interface TouristSpotRepository extends JpaRepository<TouristSpot, Long>, TouristSpotRepositoryCustom {
}
