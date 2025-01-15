package org.tukcapstone.jetsetgo.domain.expense.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.expense.entity.enums.ExpenseType;
import org.tukcapstone.jetsetgo.domain.travelPlan.entity.TravelPlan;

@Entity
@Table(name = "expense")
@Getter
@Setter
@NoArgsConstructor
public class Expense {

    @Id
    @Column(name = "expense_id", nullable = false)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ExpenseType type;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
