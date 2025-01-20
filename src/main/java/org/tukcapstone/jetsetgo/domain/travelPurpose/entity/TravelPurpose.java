package org.tukcapstone.jetsetgo.domain.travelPurpose.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "travel_purposes")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TravelPurpose {
    @Id
    @Column(name = "travel_purpose_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
}
