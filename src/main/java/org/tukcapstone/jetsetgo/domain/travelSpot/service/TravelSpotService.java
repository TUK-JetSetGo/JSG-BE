package org.tukcapstone.jetsetgo.domain.travelSpot.service;

import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse.TravelCountryInfoList;

public interface TravelSpotService {
    TravelCountryInfoList getCountryList();
}
