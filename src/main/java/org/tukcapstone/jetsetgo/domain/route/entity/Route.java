package org.tukcapstone.jetsetgo.domain.route.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.itinerary.entity.Itinerary;
import org.tukcapstone.jetsetgo.domain.route.entity.enums.TravelMode;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "itinerary_id", nullable = false)
    private Itinerary itinerary;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id", nullable = false)
    private TouristSpot touristSpot;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "visit_start_time")
    private LocalDateTime visitStartTime;

    @Column(name = "visit_end_time")
    private LocalDateTime visitEndTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "travel_mode", nullable = false)
    private TravelMode travelMode;

}
