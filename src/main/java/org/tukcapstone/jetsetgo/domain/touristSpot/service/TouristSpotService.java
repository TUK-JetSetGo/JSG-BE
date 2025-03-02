package org.tukcapstone.jetsetgo.domain.touristSpot.service;

import org.springframework.data.domain.Pageable;
import org.tukcapstone.jetsetgo.domain.touristSpot.dto.TouristSpotResponse;

import java.io.IOException;

public interface TouristSpotService {
    void importTouristSpotList(String jsonFileName) throws IOException;

    TouristSpotResponse.PagedTouristSpotInfo searchTouristSpot(String keyword, String category, Pageable pageable);
}
