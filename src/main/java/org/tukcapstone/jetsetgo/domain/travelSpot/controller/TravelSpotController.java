package org.tukcapstone.jetsetgo.domain.travelSpot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse.TravelCityInfoList;
import org.tukcapstone.jetsetgo.domain.travelSpot.dto.TravelSpotResponse.TravelCountryInfoList;
import org.tukcapstone.jetsetgo.domain.travelSpot.service.TravelSpotService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;

import static org.tukcapstone.jetsetgo.global.response.result.code.TravelResultCode.GET_CITY_LIST;
import static org.tukcapstone.jetsetgo.global.response.result.code.TravelResultCode.GET_COUNTRY_LIST;

@RestController
@RequestMapping("/travel-spots")
@Tag(name = "02. 국가 시 & 도 API", description = "국가 시 & 도 도메인의 API 입니다.")
@RequiredArgsConstructor
public class TravelSpotController {
    private final TravelSpotService travelSpotService;

    @GetMapping("/country")
    @Operation(
            summary = "국가 조회 API",
            description = """
                    국가를 조회합니다.
                    ### Example JSON:
                    ```json
                    "travelCountryInfoList": [
                        {
                          "id": 1,
                          "name": "대한민국"
                        },
                        {
                          "id": 2,
                          "name": "일본"
                        }
                    ]
                    ```
                    """
    )
    public ResultResponse<TravelCountryInfoList> getCountryList(){
        return ResultResponse.onSuccess(GET_COUNTRY_LIST, travelSpotService.getCountryList());
    }

    @GetMapping("/city/{countryId}")
    @Operation(
            summary = "시 & 도 조회 API",
            description = """
                    해당 국가의 시 / 도를 조회합니다.
                    ### Example JSON:
                    ```json
                    "travelCityInfoList": [
                        {
                          "id": 1,
                          "name": "서울특별시"
                        },
                        {
                          "id": 2,
                          "name": "부산광역시"
                        }, ...
                    ]
                    ```
                    """
    )
    public ResultResponse<TravelCityInfoList> getCityList(@PathVariable Long countryId){
        return ResultResponse.onSuccess(GET_CITY_LIST, travelSpotService.getCityList(countryId));
    }
}
