package org.tukcapstone.jetsetgo.domain.travelSpot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.travelSpot.converter.TravelSpotConverter;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse.TravelCountryInfoList;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCountry;
import org.tukcapstone.jetsetgo.domain.travelSpot.repository.TravelCityRepository;
import org.tukcapstone.jetsetgo.domain.travelSpot.repository.TravelCountryRepository;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.TravelSpotErrorCode;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelSpotServiceImpl implements TravelSpotService {
    private final TravelCountryRepository travelCountryRepository;
    private final TravelCityRepository travelCityRepository;
    private final TravelSpotConverter travelSpotConverter;

    @Override
    @Transactional(readOnly = true)
    public TravelCountryInfoList getCountryList() {
        List<TravelCountry> countryList = travelCountryRepository.findAll();
        return travelSpotConverter.toTravelCountryInfoList(countryList);
    }

    @Override
    @Transactional(readOnly = true)
    public TravelSpotResponse.TravelCityInfoList getCityList(Long countryId) {
        TravelCountry travelCountry = travelCountryRepository.findById(countryId)
                .orElseThrow(() -> new GeneralException(TravelSpotErrorCode.NOT_FOUND_COUNTRY));

        List<TravelCity> travelCityList = travelCityRepository.findByTravelCountry(travelCountry);
        return travelSpotConverter.toTravelCityInfoList(travelCityList);
    }
}
