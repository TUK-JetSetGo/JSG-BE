package org.tukcapstone.jetsetgo.domain.touristSpot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.tukcapstone.jetsetgo.domain.mission.entity.Mission;
import org.tukcapstone.jetsetgo.domain.recommendation.recommands.Recommands;
import org.tukcapstone.jetsetgo.domain.route.entity.Route;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;

@Entity
@Table(name = "tourist_spots")
@Getter
@Setter
@NoArgsConstructor
public class TouristSpot {

    @Id
    @Column(name = "tourist_spot_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "activity_level")
    private String activityLevel;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private TravelCity travelCity;

    @OneToMany(mappedBy = "touristSpot")
    private List<Recommands> recommendations = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<Route> routes = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<TouristSpotPurpose> touristSpotPurposes = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<TouristSpotTheme> touristSpotThemes = new ArrayList<>();
}
