package org.tukcapstone.jetsetgo.domain.touristSpot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.touristSpot.converter.TouristSpotConverter;
import org.tukcapstone.jetsetgo.domain.touristSpot.dto.TouristSpotResponse;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;
import org.tukcapstone.jetsetgo.domain.touristSpot.repository.TouristSpotRepository;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelSpot.repository.TravelCityRepository;
import org.tukcapstone.jetsetgo.global.response.exception.ErrorCode;
import org.tukcapstone.jetsetgo.global.response.exception.GeneralException;
import org.tukcapstone.jetsetgo.global.response.exception.code.CommonErrorCode;
import org.tukcapstone.jetsetgo.global.response.exception.code.TravelErrorCode;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TouristSpotServiceImpl implements TouristSpotService {
    private final TouristSpotRepository touristSpotRepository;
    private final TravelCityRepository travelCityRepository;
    private final TouristSpotConverter touristSpotConverter;

    public void importTouristSpots(String jsonFilePath) throws IOException {
        // JSON 파일 로드
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> touristSpots = objectMapper.readValue(new File(jsonFilePath), List.class);

        // 데이터 삽입
        for (Map<String, Object> spot : touristSpots) {
            TouristSpot touristSpot = new TouristSpot();

            // 기본 속성 매핑
            touristSpot.setName((String) spot.get("name"));
            touristSpot.setTel((String) spot.get("tel"));
            touristSpot.setLatitude(Double.parseDouble(spot.get("y").toString()));
            touristSpot.setLongitude(Double.parseDouble(spot.get("x").toString()));
            touristSpot.setActivityLevel("");
            touristSpot.setAddress((String) spot.get("address"));

            // 카테고리, 사업 상태, 기타 필드 처리
            touristSpot.setCategory(objectMapper.writeValueAsString(spot.getOrDefault("category", List.of())));
            touristSpot.setHomePage((String) spot.get("home_page"));
            touristSpot.setNaverBookingUrl((String) spot.get("naverBookingUrl"));

            Map<String, Object> businessStatusMap = (Map<String, Object>) spot.get("businessStatus");
            if (businessStatusMap != null) {
                String detailInfo = (String) ((Map<String, Object>) businessStatusMap.get("status")).get("detailInfo");

                // detailInfo가 null일 경우 빈 문자열로 설정
                if (detailInfo == null) {
                    detailInfo = "";
                }

                // 단순히 detailInfo만 문자열로 설정
                touristSpot.setBusinessStatus(detailInfo);
            }

            // thumbnailUrls 처리 (목록으로 변환하여 JSON 형태로 저장)
            List<String> thumbnailUrls = (List<String>) spot.get("thumUrls");
            if (thumbnailUrls != null && !thumbnailUrls.isEmpty()) {
                touristSpot.setThumbnailUrls(objectMapper.writeValueAsString(thumbnailUrls));  // JSON 형식으로 저장
            } else {
                touristSpot.setThumbnailUrls(objectMapper.writeValueAsString(List.of()));  // 비어있는 리스트를 JSON 형식으로 저장
            }

            // 단일 thumbnailUrl 처리 (String으로 저장)
            touristSpot.setThumbnailUrl((String) spot.get("thumUrl"));

            // TravelCity 엔티티를 조회하여 설정
            TravelCity travelCity = travelCityRepository.findById(9L)
                    .orElseThrow(() -> new GeneralException(TravelErrorCode.NOT_FOUND_CITY));

            touristSpot.setTravelCity(travelCity);

            // 저장
            touristSpotRepository.save(touristSpot);
        }
    }

    public TouristSpotResponse.PagedTouristSpotInfo searchTouristSpot(String keyword, String category, Pageable pageable) {
        Page<TouristSpot> touristSpotPage = touristSpotRepository.searchByDynamicQuery(keyword, category, pageable);
        return touristSpotConverter.toPagedTouristSpotInfo(touristSpotPage);
    }
}
