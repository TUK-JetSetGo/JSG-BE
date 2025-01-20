package org.tukcapstone.jetsetgo.domain.travelPlan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.shareGroup.converter.ShareGroupConverter;
import org.tukcapstone.jetsetgo.domain.shareGroup.entity.ShareGroup;
import org.tukcapstone.jetsetgo.domain.shareGroup.repository.ShareGroupRepository;
import org.tukcapstone.jetsetgo.domain.travelPlan.converter.TravelPlanConverter;
import org.tukcapstone.jetsetgo.domain.travelPlan.dto.TravelPlanRequest.CreateTravelPlanRequest;
import org.tukcapstone.jetsetgo.domain.travelPlan.entity.TravelPlan;
import org.tukcapstone.jetsetgo.domain.travelPlan.repository.TravelPlanRepository;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelPurpose.repository.TravelPurposeRepository;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelSpot.repository.TravelCityRepository;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;
import org.tukcapstone.jetsetgo.domain.travelTheme.repository.TravelThemeRepository;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.TravelErrorCode;

@Service
@RequiredArgsConstructor
public class TravelPlanServiceImpl implements TravelPlanService {
    private final TravelPlanRepository travelPlanRepository;
    private final TravelCityRepository travelCityRepository;
    private final TravelPurposeRepository travelPurposeRepository;
    private final TravelThemeRepository travelThemeRepository;
    private final ShareGroupRepository shareGroupRepository;
    private final TravelPlanConverter travelPlanConverter;
    private final ShareGroupConverter shareGroupConverter;

    @Override
    @Transactional
    public void createTravelPlan(CreateTravelPlanRequest request) {
        // 여행 시 & 도 조회
        TravelCity travelCity = travelCityRepository.findById(request.getTravelCityId())
                .orElseThrow(() -> new GeneralException(TravelErrorCode.NOT_FOUND_CITY));

        // 여행 목적 조회
        TravelPurpose travelPurpose = travelPurposeRepository.findById(request.getTravelPurposeId())
                .orElseThrow(() -> new GeneralException(TravelErrorCode.NOT_FOUND_PURPOSE));

        // 여행 테마 조회
        TravelTheme travelTheme = travelThemeRepository.findById(request.getTravelThemeId())
                .orElseThrow(() -> new GeneralException(TravelErrorCode.NOT_FOUND_THEME));

        // TravelPlan 엔티티 생성 및 저장
        TravelPlan travelPlan = travelPlanConverter.toEntity(request, travelCity, travelPurpose, travelTheme);
        travelPlanRepository.save(travelPlan);

        // 그룹 생성 로직
        if (request.getIsGroup()) {
            ShareGroup shareGroup = shareGroupConverter.toEntity(request.getGroupSize());
            shareGroupRepository.save(shareGroup);
        } else {
            ShareGroup shareGroup = shareGroupConverter.toEntity();
            shareGroupRepository.save(shareGroup);
        }
    }
}
