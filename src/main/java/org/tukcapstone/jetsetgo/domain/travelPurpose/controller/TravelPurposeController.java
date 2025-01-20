package org.tukcapstone.jetsetgo.domain.travelPurpose.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.travelPurpose.dto.TravelPurposeResponse.TravelPurposeInfoList;
import org.tukcapstone.jetsetgo.domain.travelPurpose.service.TravelPurposeService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;

import static org.tukcapstone.jetsetgo.global.response.result.code.TravelResultCode.GET_PURPOSE_LIST;

@RestController
@RequestMapping("/purposes")
@Tag(name = "03. 여행 목적 API", description = "여행 목적 도메인의 API 입니다.")
@RequiredArgsConstructor
public class TravelPurposeController {
    private final TravelPurposeService travelPurposeService;

    @GetMapping
    @Operation(
            summary = "여행 목적 조회 API",
            description = """
                    여행 목적을 조회합니다.
                    ### Example JSON:
                    ```json
                    "travelPurposeInfoList": [
                          {
                            "id": 1,
                            "name": "혼자서"
                          },
                          {
                            "id": 2,
                            "name": "가족과"
                          }, ...
                    ]
                    ```
                    """
    )
    public ResultResponse<TravelPurposeInfoList> getPurposeList(){
        return ResultResponse.onSuccess(GET_PURPOSE_LIST, travelPurposeService.getPurposeList());
    }
}
