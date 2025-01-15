package org.tukcapstone.jetsetgo.domain.travelPlan.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.checklist.entity.Checklist;
import org.tukcapstone.jetsetgo.domain.expense.entity.Expense;
import org.tukcapstone.jetsetgo.domain.group.entity.Group;
import org.tukcapstone.jetsetgo.domain.itinerary.entity.Itinerary;
import org.tukcapstone.jetsetgo.domain.travelCity.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;

@Entity
@Table(name = "travel_plan")
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private TravelTheme travelTheme;

    @ManyToOne
    @JoinColumn(name = "purpose_id", nullable = false)
    private TravelPurpose purposeId;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private TravelCity travelCity;

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Checklist> checklists = new ArrayList<>();

    @OneToMany(mappedBy = "travelPlan", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Itinerary> itineraries = new ArrayList<>();


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
