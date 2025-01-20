package org.tukcapstone.jetsetgo.domain.travelTheme.converter;

import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.travelTheme.dto.TravelThemeResponse.TravelThemeInfo;
import org.tukcapstone.jetsetgo.domain.travelTheme.dto.TravelThemeResponse.TravelThemeInfoList;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;

import java.util.List;

@Component
public class TravelThemeConverter {
    public TravelThemeInfo toTravelThemeInfo(TravelTheme travelTheme) {
        return TravelThemeInfo.builder()
                .id(travelTheme.getId())
                .name(travelTheme.getName())
                .build();
    }

    public TravelThemeInfoList toTravelThemeInfoList(List<TravelTheme> travelThemeList) {
        List<TravelThemeInfo> travelThemeInfoList = travelThemeList.stream()
                .map(this::toTravelThemeInfo)
                .toList();

        return new TravelThemeInfoList(travelThemeInfoList);
    }
}
