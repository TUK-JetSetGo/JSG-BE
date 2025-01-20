package org.tukcapstone.jetsetgo.domain.travelPlan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tukcapstone.jetsetgo.domain.shareGroup.entity.ShareGroup;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;
import org.tukcapstone.jetsetgo.global.entity.BaseTimeEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "travel_plans")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TravelPlan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_plan_id")
    private Long id;

    @Column(nullable = false)
    private Integer dailyVisitCount;

    @Column(nullable = false)
    private LocalDate travelStartDate;

    @Column(nullable = false)
    private LocalDate travelEndDate;

    @Column(nullable = false)
    private LocalTime activityStartTime;

    @Column(nullable = false)
    private LocalTime activityEndTime;

    @Column(nullable = false)
    private Integer budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "share_group_id")
    private ShareGroup shareGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_theme_id")
    private TravelTheme travelTheme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_purpose_id")
    private TravelPurpose travelPurpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_city_id")
    private TravelCity travelCity;
}
