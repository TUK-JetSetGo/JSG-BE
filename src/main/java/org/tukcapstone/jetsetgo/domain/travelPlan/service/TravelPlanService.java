package org.tukcapstone.jetsetgo.domain.travelPlan.service;

import org.tukcapstone.jetsetgo.domain.travelPlan.dto.TravelPlanRequest.CreateTravelPlanRequest;

public interface TravelPlanService {
    void createTravelPlan(CreateTravelPlanRequest request);
}
