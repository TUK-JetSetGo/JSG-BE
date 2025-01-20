package org.tukcapstone.jetsetgo.domain.travelSpot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public abstract class TravelSpotResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TravelCountryInfo {
        private Long countryId;
        private String countryName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TravelCountryInfoList {
        private List<TravelCountryInfo> travelCountryInfoList;
    }
}
