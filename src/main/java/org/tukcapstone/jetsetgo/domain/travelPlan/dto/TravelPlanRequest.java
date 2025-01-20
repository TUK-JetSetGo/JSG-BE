package org.tukcapstone.jetsetgo.domain.travelPlan.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public abstract class TravelPlanRequest {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateTravelPlanRequest {
        @NotEmpty(message = "그룹 여부는 필수 입력 항목입니다.")
        private Boolean isGroup; // 그룹인지 여부

        @NotEmpty(message = "그룹 인원은 필수 입력 항목입니다.")
        private Integer groupSize; // 그룹 인원

        @NotEmpty(message = "여행 시/도 ID는 필수 입력 항목입니다.")
        private Long travelCityId; // 여행 시/도 ID

        @NotEmpty(message = "하루에 방문할 관광지 개수는 필수 입력 항목입니다.")
        private Integer dailyVisitCount;

        @NotEmpty(message = "활동 시작 시간은 필수 입력 항목입니다.")
        private String activityStartTime; // 활동 시작 시간

        @NotEmpty(message = "활동 종료 시간은 필수 입력 항목입니다.")
        private String activityEndTime; // 활동 종료 시간

        @NotEmpty(message = "여행 시작 날짜는 필수 입력 항목입니다.")
        private LocalDate travelStartDate; // 여행 시작 일

        @NotEmpty(message = "여행 종료 날짜는 필수 입력 항목입니다.")
        private LocalDate travelEndDate; // 여행 종료 일

        @NotEmpty(message = "여행 목적 ID는 필수 입력 항목입니다.")
        private Long travelPurposeId; // 여행 목적 ID

        @NotEmpty(message = "여행 테마 ID는 필수 입력 항목입니다.")
        private Long travelThemeId; // 여행 테마 ID

        @NotEmpty(message = "여행 예산은 필수 입력 항목입니다.")
        private Integer budget; // 여행 예산

        private List<Long> travelSpotIdList; // 방문지 ID 리스트
    }
}
