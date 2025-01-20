package org.tukcapstone.jetsetgo.domain.travelPlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tukcapstone.jetsetgo.domain.travelPlan.entity.TravelPlan;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
}
