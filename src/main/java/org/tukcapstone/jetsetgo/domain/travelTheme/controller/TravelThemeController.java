package org.tukcapstone.jetsetgo.domain.travelTheme.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.travelTheme.dto.TravelThemeResponse.TravelThemeInfoList;
import org.tukcapstone.jetsetgo.domain.travelTheme.service.TravelThemeService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;

import static org.tukcapstone.jetsetgo.global.response.result.code.TravelThemeResultCode.GET_THEME_LIST;

@RestController
@RequestMapping("/themes")
@Tag(name = "04. 여행 테마 API", description = "여행 테마 도메인의 API 입니다.")
@RequiredArgsConstructor
public class TravelThemeController {
    private final TravelThemeService travelThemeService;

    @GetMapping
    @Operation(
            summary = "여행 테마 조회 API",
            description = """
                    여행 테마를 조회합니다.
                    ### Example JSON:
                    ```json
                    "travelThemeInfoList": [
                          {
                            "id": 1,
                            "name": "스포츠/액티비티"
                          },
                          {
                            "id": 2,
                            "name": "엔터테인먼트"
                          }, ...
                    ]
                    """
    )
    public ResultResponse<TravelThemeInfoList> getThemeList() {
        return ResultResponse.onSuccess(GET_THEME_LIST, travelThemeService.getThemeList());
    }
}
