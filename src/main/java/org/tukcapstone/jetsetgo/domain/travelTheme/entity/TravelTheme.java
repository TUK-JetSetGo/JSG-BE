package org.tukcapstone.jetsetgo.domain.travelTheme.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpotTheme;

@Entity
@Table(name = "travel_themes")
@Getter
@Setter
@NoArgsConstructor
public class TravelTheme {

    @Id
    @Column(name = "travel_theme id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "travelTheme")
    private List<TouristSpotTheme> touristSpotThemes = new ArrayList<>();
}
