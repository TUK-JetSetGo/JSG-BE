package org.tukcapstone.jetsetgo.domain.travelPlan.converter;

import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.travelPlan.dto.TravelPlanRequest;
import org.tukcapstone.jetsetgo.domain.travelPlan.entity.TravelPlan;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class TravelPlanConverter {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public TravelPlan toEntity(TravelPlanRequest.CreateTravelPlanRequest request, TravelCity travelCity, TravelPurpose travelPurpose, TravelTheme travelTheme) {
        return TravelPlan.builder()
                .dailyVisitCount(request.getDailyVisitCount())
                .travelStartDate(request.getTravelStartDate())
                .travelEndDate(request.getTravelEndDate())
                .activityStartTime(LocalTime.parse(request.getActivityStartTime(), TIME_FORMATTER))
                .activityEndTime(LocalTime.parse(request.getActivityEndTime(), TIME_FORMATTER))
                .budget(request.getBudget())
                .travelCity(travelCity)
                .travelPurpose(travelPurpose)
                .travelTheme(travelTheme)
                .build();
    }
}
