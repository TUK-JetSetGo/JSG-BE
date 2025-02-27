package org.tukcapstone.jetsetgo.domain.touristSpot.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.QTouristSpot;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TouristSpotRepositoryImpl implements TouristSpotRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TouristSpot> searchByDynamicQuery(String keyword, String category, Pageable pageable) {
        QTouristSpot touristSpot = QTouristSpot.touristSpot;

        BooleanBuilder builder = new BooleanBuilder();

        if (keyword != null && !keyword.isBlank()) {
            builder.and(
                    touristSpot.name.containsIgnoreCase(keyword)
                            .or(touristSpot.address.containsIgnoreCase(keyword))
            );
        }

        if (category != null && !category.isBlank()) {
            builder.and(touristSpot.category.containsIgnoreCase(category));
        }

        // 전체 결과를 먼저 조회하여 페이지 수를 계산
        List<TouristSpot> places = queryFactory
                .selectFrom(touristSpot)
                .where(builder)
                .offset(pageable.getOffset()) // 페이지 번호에 맞춰서 오프셋 설정
                .limit(pageable.getPageSize()) // 한 페이지에 나올 데이터 수
                .fetch();

        // 전체 데이터 개수 조회
        long total = queryFactory
                .selectFrom(touristSpot)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(places, pageable, total); // 페이지 정보를 포함한 결과 반환
    }
}
