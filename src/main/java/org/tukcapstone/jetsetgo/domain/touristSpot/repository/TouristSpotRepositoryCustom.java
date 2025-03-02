package org.tukcapstone.jetsetgo.domain.touristSpot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

public interface TouristSpotRepositoryCustom {
    Page<TouristSpot> searchByDynamicQuery(String keyword, String category, Pageable pageable);
}
