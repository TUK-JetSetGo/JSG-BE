package org.tukcapstone.jetsetgo.domain.travelPlan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
import org.tukcapstone.jetsetgo.domain.group.enitty.Group;
import org.tukcapstone.jetsetgo.domain.itinerary.entity.Itinerary;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;

@Entity
@Table(name = "travel_plans")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TravelPlan {
    @Id
    @Column(name = "travel_plan_id", nullable = false)
    private Long travelPlanId;

    @Column(name = "budget", nullable = false)
    private Integer budget;

    @Column(name = "travel_start_date", nullable = false)
    private LocalDate travelStartDate;

    @Column(name = "travel_end_date", nullable = false)
    private LocalDate travelEndDate;

    @Column(name = "activity_start_time")
    private LocalDateTime activityStartTime;

    @Column(name = "activity_end_time")
    private LocalDateTime activityEndTime;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private TravelTheme travelTheme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id", nullable = false)
    private TravelPurpose purposeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private TravelCity travelCity;

    @OneToMany(mappedBy = "travelPlan")
    private List<Expense> expenseList = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan")
    private List<Checklist> checklistList = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan")
    private List<Itinerary> itineraryList = new ArrayList<>();
}
