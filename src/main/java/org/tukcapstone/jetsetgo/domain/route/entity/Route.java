package org.tukcapstone.jetsetgo.domain.route.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.tukcapstone.jetsetgo.domain.itinerary.entity.Itinerary;
import org.tukcapstone.jetsetgo.domain.route.entity.enums.TravelMode;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(nullable = false)
    private LocalDateTime visitStartTime;

    @Column(nullable = false)
    private LocalDateTime visitEndTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TravelMode travelMode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourist_spot_id")
    private TouristSpot touristSpot;
}
