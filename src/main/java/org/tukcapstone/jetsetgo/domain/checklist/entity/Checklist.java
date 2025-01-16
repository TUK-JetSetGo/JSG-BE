package org.tukcapstone.jetsetgo.domain.checklist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.travelPlan.entity.TravelPlan;

@Entity
@Table(name = "checklists")
@Getter
@Setter
@NoArgsConstructor
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
