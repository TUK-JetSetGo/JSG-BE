package org.tukcapstone.jetsetgo.domain.travelSpot.converter;

import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse.TravelCountryInfo;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse.TravelCountryInfoList;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCountry;

import java.util.List;

@Component
public class TravelSpotConverter {
    public TravelCountryInfo toTravelCountryInfo(TravelCountry travelCountry) {
        return TravelCountryInfo.builder()
                .countryId(travelCountry.getId())
                .countryName(travelCountry.getCountryName())
                .build();
    }

    public TravelCountryInfoList toTravelCountryInfoList(List<TravelCountry> travelCountryList) {
        List<TravelCountryInfo> travelCountryInfoList = travelCountryList.stream()
                .map(this::toTravelCountryInfo)
                .toList();

        return new TravelCountryInfoList(travelCountryInfoList);
    }
}
