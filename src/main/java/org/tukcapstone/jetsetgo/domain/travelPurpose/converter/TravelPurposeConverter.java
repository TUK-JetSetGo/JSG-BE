package org.tukcapstone.jetsetgo.domain.travelPurpose.converter;

import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.travelPurpose.dto.TravelPurposeResponse.TravelPurposeInfo;
import org.tukcapstone.jetsetgo.domain.travelPurpose.dto.TravelPurposeResponse.TravelPurposeInfoList;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;

import java.util.List;

@Component
public class TravelPurposeConverter {
    public TravelPurposeInfo toTravelPurposeInfo(TravelPurpose travelPurpose) {
        return TravelPurposeInfo.builder()
                .id(travelPurpose.getId())
                .name(travelPurpose.getName())
                .build();
    }

    public TravelPurposeInfoList toTravelPurposeInfoList(List<TravelPurpose> travelPurposeList) {
        List<TravelPurposeInfo> travelPurposeInfoList = travelPurposeList.stream()
                .map(this::toTravelPurposeInfo)
                .toList();

        return new TravelPurposeInfoList(travelPurposeInfoList);
    }
}
