package org.tukcapstone.jetsetgo.domain.travelTheme.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/themes")
@Tag(name = "04. 여행 테마 API", description = "여행 테마 도메인의 API 입니다.")
@RequiredArgsConstructor
public class TravelThemeController {
}
