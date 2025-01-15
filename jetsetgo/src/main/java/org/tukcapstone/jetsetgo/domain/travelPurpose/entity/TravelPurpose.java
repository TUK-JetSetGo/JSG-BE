package org.tukcapstone.jetsetgo.domain.travelPurpose.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

@Entity
@Table(name = "travel_purpose")
@Getter
@Setter
@NoArgsConstructor
public class TravelPurpose {

    @Id
    @Column(name = "travel_purpose_id", nullable = false)
    private Long travelPurposeId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "travelPurposes")
    private List<TouristSpot> touristSpots = new ArrayList<>();
}
