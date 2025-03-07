package org.tukcapstone.jetsetgo.domain.travelSpot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public abstract class TravelSpotResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelCountryInfo {
        private Long id;
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelCountryInfoList {
        private List<TravelCountryInfo> travelCountryInfoList;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelCityInfo {
        private Long id;
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelCityInfoList {
        private List<TravelCityInfo> travelCityInfoList;
    }
}
