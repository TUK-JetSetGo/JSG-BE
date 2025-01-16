package org.tukcapstone.jetsetgo.domain.touristSpot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;

@Entity
@Table(name = "tourist_spot_purposes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristSpotPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourist_spot_id", nullable = false)
    private TouristSpot touristSpot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_purpose_id", nullable = false)
    private TravelPurpose travelPurpose;
}
