package org.tukcapstone.jetsetgo.domain.travelPlan.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tukcapstone.jetsetgo.domain.checklist.entity.Checklist;
import org.tukcapstone.jetsetgo.domain.expense.entity.Expense;
import org.tukcapstone.jetsetgo.domain.group.enitty.ShareGroup;
import org.tukcapstone.jetsetgo.domain.itinerary.entity.Itinerary;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;
import org.tukcapstone.jetsetgo.global.entity.BaseTimeEntity;

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
    private Integer budget;

    @Column(nullable = false)
    private LocalDate travelStartDate;

    @Column(nullable = false)
    private LocalDate travelEndDate;

    @Column(nullable = false)
    private LocalDateTime activityStartTime;

    @Column(nullable = false)
    private LocalDateTime activityEndTime;

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

    @OneToMany(mappedBy = "travelPlan")
    private List<Expense> expenseList = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan")
    private List<Checklist> checklistList = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan")
    private List<Itinerary> itineraryList = new ArrayList<>();
}
