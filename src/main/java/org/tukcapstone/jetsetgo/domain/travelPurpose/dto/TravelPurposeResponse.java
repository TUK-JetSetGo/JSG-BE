package org.tukcapstone.jetsetgo.domain.travelPurpose.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public abstract class TravelPurposeResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelPurposeInfo {
        private Long id;
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelPurposeInfoList {
        private List<TravelPurposeInfo> travelPurposeInfoList;
    }
}
