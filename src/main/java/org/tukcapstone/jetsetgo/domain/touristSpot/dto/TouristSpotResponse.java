package org.tukcapstone.jetsetgo.domain.touristSpot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public abstract class TouristSpotResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class TouristSpotInfo {
        private Long id;
        private String name;
        private String tel;
        private String category;
        private String businessStatus;
        private String address;
        private String thumbnailUrl;
        private String thumbnailUrls;
        private Double latitude;
        private Double longitude;
        private String activityLevel;
        private String homePage;
        private String naverBookingUrl;
        private Long travelCityId;  // 여행 도시 ID
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PagedTouristSpotInfo {
        private List<TouristSpotInfo> touristSpotInfoList;
        private Integer totalPages;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
