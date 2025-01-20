package org.tukcapstone.jetsetgo.domain.travelSpot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse;
import org.tukcapstone.jetsetgo.domain.travelSpot.service.TravelSpotService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;

import static org.tukcapstone.jetsetgo.global.response.result.code.TravelSpotResultCode.GET_COUNTRY_LIST;

@RestController
@RequestMapping("/travel-spots")
@Tag(name = "02. 국가 시 & 도 API", description = "국가 시 & 도 API")
@RequiredArgsConstructor
public class TravelSpotController {
    private final TravelSpotService travelSpotService;

    @GetMapping("/country")
    @Operation(
            summary = "국가 조회",
            description = """
                    국가를 조회합니다.
                    ### Example JSON:
                    ```json
                    "travelCountryInfoList": [
                        {
                          "countryId": 1,
                          "countryName": "대한민국"
                        },
                        {
                          "countryId": 2,
                          "countryName": "일본"
                        }
                    ]
                    ```
                    """)
    public ResultResponse<TravelSpotResponse.TravelCountryInfoList> getCountryList(){
        return ResultResponse.onSuccess(GET_COUNTRY_LIST, travelSpotService.getCountryList());
    }

}
