package org.tukcapstone.jetsetgo.domain.travelSpot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

@Entity
@Table(name = "travel_cities")
@Getter
@Setter
@NoArgsConstructor
public class TravelCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private TravelCountry country;

    @OneToMany(mappedBy = "travelCity")
    private List<TouristSpot> touristSpots = new ArrayList<>();
}
