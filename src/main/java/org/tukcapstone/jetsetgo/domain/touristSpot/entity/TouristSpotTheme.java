package org.tukcapstone.jetsetgo.domain.touristSpot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;

@Entity
@Table(name = "tourist_spot_themes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristSpotTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourist_spot_id", nullable = false)
    private TouristSpot touristSpot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_theme_id", nullable = false)
    private TravelTheme travelTheme;

}
