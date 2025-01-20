package org.tukcapstone.jetsetgo.domain.travelTheme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public abstract class TravelThemeResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelThemeInfo {
        private Long id;
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TravelThemeInfoList {
        private List<TravelThemeInfo> travelThemeInfoList;
    }
}
