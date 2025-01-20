package org.tukcapstone.jetsetgo.domain.travelPurpose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;

public interface TravelPurposeRepository extends JpaRepository<TravelPurpose, Long> {
}
