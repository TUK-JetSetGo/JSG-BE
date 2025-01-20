package org.tukcapstone.jetsetgo.domain.travelSpot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.travelSpot.converter.TravelSpotConverter;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse.TravelCountryInfoList;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCountry;
import org.tukcapstone.jetsetgo.domain.travelSpot.repository.TravelCountryRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelSpotServiceImpl implements TravelSpotService {
    private final TravelCountryRepository travelCountryRepository;
    private final TravelSpotConverter travelSpotConverter;

    @Override
    @Transactional(readOnly = true)
    public TravelCountryInfoList getCountryList() {
        List<TravelCountry> countryList = travelCountryRepository.findAll();
        return travelSpotConverter.toTravelCountryInfoList(countryList);
    }
}
