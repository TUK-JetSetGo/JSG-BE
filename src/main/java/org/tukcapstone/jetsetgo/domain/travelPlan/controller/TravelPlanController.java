package org.tukcapstone.jetsetgo.domain.travelPlan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.travelPlan.dto.TravelPlanRequest.CreateTravelPlanRequest;
import org.tukcapstone.jetsetgo.domain.travelPlan.service.TravelPlanService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;
import org.tukcapstone.jetsetgo.global.response.result.code.TravelResultCode;

import static org.tukcapstone.jetsetgo.global.response.result.code.TravelResultCode.CREATE_TRAVEL_PLAN;

@RestController
@RequestMapping("/travel-plans")
@Tag(name = "05. 여행 일정 API", description = "여행 일정 도메인의 API 입니다.")
@RequiredArgsConstructor
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    @PostMapping("/create")
    @Operation(
            summary = "여행 일정 생성 API",
            description = """
                    여행 일정을 생성합니다.
                    ### Example JSON:
                    - 개인을 선택했을 경우
                    ```json
                    {
                      "isGroup": false,
                      "groupSize": 1,
                      "travelCityId": 1,
                      "dailyVisitCount": 5,
                      "activityStartTime": "10:00:00",
                      "activityEndTime": "20:00:00",
                      "travelStartDate": "2025-01-20",
                      "travelEndDate": "2025-01-25",
                      "travelPurposeId": 1,
                      "travelThemeId": 1,
                      "budget": 1000000000
                    }
                    ```
                    
                    - 그룹을 선택했을 경우
                    ```json
                    {
                      "isGroup": true,
                      "groupSize": 4,
                      "travelCityId": 1,
                      "dailyVisitCount": 3,
                      "activityStartTime": "10:00:00",
                      "activityEndTime": "20:00:00",
                      "travelStartDate": "2025-01-20",
                      "travelEndDate": "2025-01-25",
                      "travelPurposeId": 1,
                      "travelThemeId": 1,
                      "budget": 1000000000
                    }
                    ```
                    """
    )
    public ResultResponse<TravelResultCode> createTravelPlan(@Valid @RequestBody CreateTravelPlanRequest request) {
        travelPlanService.createTravelPlan(request);
        return ResultResponse.onSuccess(CREATE_TRAVEL_PLAN);
    }
}
