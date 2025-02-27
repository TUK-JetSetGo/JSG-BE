package org.tukcapstone.jetsetgo.domain.touristSpot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.touristSpot.dto.TouristSpotResponse.PagedTouristSpotInfo;
import org.tukcapstone.jetsetgo.domain.touristSpot.service.TouristSpotService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;

import java.io.IOException;

import static org.tukcapstone.jetsetgo.global.response.result.code.TravelResultCode.GET_TOURIST_SPOT_LIST;

@RestController
@RequiredArgsConstructor
@Tag(name = "07. 여행지 API", description = "여행지 관련 API 입니다.")
@RequestMapping("/tourist-spots")
public class TouristSpotController {
    private final TouristSpotService touristSpotService;

    @PostMapping("/import")
    @Operation(summary = "관광지 데이터 삽입 API", description = "관광지 데이터를 삽입하는 API 입니다.")
    public String importTouristSpots(@RequestParam String jsonFileName) {
        String jsonFilePath = "/Users/jeongdonghun/JSG-BE/" + jsonFileName;

        try {
            touristSpotService.importTouristSpots(jsonFilePath);
            return "데이터 삽입 완료!";
        } catch (IOException e) {
            return "파일 처리 오류: " + e.getMessage();
        }
    }

    @GetMapping("/search")
    @Operation(summary = "관광지 조회 API", description = "카테고리와 키워드를 사용하여 관광지를 조회하는 API 입니다.")
    public ResultResponse<PagedTouristSpotInfo> searchTouristSpotList(@RequestParam(required = false) String keyword,
                                                                      @RequestParam(required = false) String category,
                                                                      Pageable pageable) {
        return ResultResponse.onSuccess(GET_TOURIST_SPOT_LIST, touristSpotService.searchTouristSpot(keyword, category, pageable));
    }
}
