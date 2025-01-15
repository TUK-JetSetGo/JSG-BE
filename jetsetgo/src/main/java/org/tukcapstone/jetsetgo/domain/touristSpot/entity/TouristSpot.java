package org.tukcapstone.jetsetgo.domain.touristSpot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import javax.xml.crypto.KeySelector.Purpose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.mission.entity.Mission;
import org.tukcapstone.jetsetgo.domain.recommendation.domain.Recommendation;
import org.tukcapstone.jetsetgo.domain.route.entity.Route;
import org.tukcapstone.jetsetgo.domain.travelCity.entity.TravelCity;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;

@Entity
@Table(name = "tourist_spot")
@Getter
@Setter
@NoArgsConstructor
public class TouristSpot {

    @Id
    @Column(name = "tourist_spot_id", nullable = false)
    private Long touristSpotId;

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
    private List<Recommendation> recommendations = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<Route> routes = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<Mission> missions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tourist_spot_purpose",
            joinColumns = @JoinColumn(name = "tourist_spot_id"),
            inverseJoinColumns = @JoinColumn(name = "travel_purpose_id")
    )
    private List<TravelPurpose> travelPurposes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tourist_spot_theme",
            joinColumns = @JoinColumn(name = "tourist_spot_id"),
            inverseJoinColumns = @JoinColumn(name = "travel_theme_id")
    )
    private List<TravelTheme> travelThemes = new ArrayList<>();

}
