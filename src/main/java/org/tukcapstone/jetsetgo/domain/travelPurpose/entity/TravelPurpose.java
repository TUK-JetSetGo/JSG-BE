package org.tukcapstone.jetsetgo.domain.travelPurpose.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpotPurpose;

@Entity
@Table(name = "travel_purposes")
@Getter
@Setter
@NoArgsConstructor
public class TravelPurpose {

    @Id
    @Column(name = "travel_purpose_id", nullable = false)
    private Long travelPurposeId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "travelPurpose")
    private List<TouristSpotPurpose> touristSpotPurposes = new ArrayList<>();
}
