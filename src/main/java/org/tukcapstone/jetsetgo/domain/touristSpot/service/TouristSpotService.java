package org.tukcapstone.jetsetgo.domain.touristSpot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tukcapstone.jetsetgo.domain.touristSpot.dto.TouristSpotResponse;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

import java.io.IOException;

public interface TouristSpotService {
    void importTouristSpots(String jsonFilePath) throws IOException;

    TouristSpotResponse.PagedTouristSpotInfo searchTouristSpot(String keyword, String category, Pageable pageable);
}
