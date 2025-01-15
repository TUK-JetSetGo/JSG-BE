package org.tukcapstone.jetsetgo.domain.travelCity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

@Entity
@Table(
        name = "travel_city",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"country_id", "city_name"})
        }
)
@Getter
@Setter
@NoArgsConstructor
public class TravelCity {

    @Id
    @Column(name = "city_id", nullable = false)
    private UUID cityId;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @OneToMany(mappedBy = "travelCity")
    private List<TouristSpot> touristSpots = new ArrayList<>();


}
