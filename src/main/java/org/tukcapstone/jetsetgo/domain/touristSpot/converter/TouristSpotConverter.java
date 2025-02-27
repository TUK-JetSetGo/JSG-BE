package org.tukcapstone.jetsetgo.domain.touristSpot.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.touristSpot.dto.TouristSpotResponse;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

import java.util.List;

@Component
public class TouristSpotConverter {
    public TouristSpotResponse.PagedTouristSpotInfo toPagedTouristSpotInfo(Page<TouristSpot> touristSpotPage) {
        List<TouristSpotResponse.TouristSpotInfo> touristSpotInfoList = touristSpotPage.stream()
                .map(this::toTouristSpotInfo)
                .toList();

        return TouristSpotResponse.PagedTouristSpotInfo.builder()
                .touristSpotInfoList(touristSpotInfoList)
                .totalPages(touristSpotPage.getTotalPages())
                .totalElements(touristSpotPage.getTotalElements())
                .isFirst(touristSpotPage.isFirst())
                .isLast(touristSpotPage.isLast())
                .build();
    }

    public TouristSpotResponse.TouristSpotInfo toTouristSpotInfo(TouristSpot touristSpot) {
        return TouristSpotResponse.TouristSpotInfo.builder()
                .id(touristSpot.getId())
                .name(touristSpot.getName())
                .tel(touristSpot.getTel())
                .category(touristSpot.getCategory())
                .businessStatus(touristSpot.getBusinessStatus())
                .address(touristSpot.getAddress())
                .thumbnailUrl(touristSpot.getThumbnailUrl())
                .thumbnailUrls(touristSpot.getThumbnailUrls())
                .latitude(touristSpot.getLatitude())
                .longitude(touristSpot.getLongitude())
                .activityLevel(touristSpot.getActivityLevel())
                .homePage(touristSpot.getHomePage())
                .naverBookingUrl(touristSpot.getNaverBookingUrl())
                .travelCityId(touristSpot.getTravelCity() != null ? touristSpot.getTravelCity().getId() : null)
                .build();
    }
}
