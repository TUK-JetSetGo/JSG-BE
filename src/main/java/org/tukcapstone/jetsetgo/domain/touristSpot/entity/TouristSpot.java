package org.tukcapstone.jetsetgo.domain.touristSpot.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.mission.entity.Mission;
import org.tukcapstone.jetsetgo.domain.recommend.entity.Recommend;
import org.tukcapstone.jetsetgo.domain.route.entity.Route;
import org.tukcapstone.jetsetgo.domain.travelSpot.entity.TravelCity;

@Entity
@Table(name = "tourist_spots")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TouristSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourist_spot_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String tel;

    @Column(columnDefinition = "JSON")
    private String category;

    @Column
    private String businessStatus;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(columnDefinition = "JSON")
    private String thumbnailUrls;  // JSON 형식으로 저장

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String activityLevel;

    @Column
    private String homePage;

    @Column
    private String naverBookingUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_city_id")
    private TravelCity travelCity;

    @OneToMany(mappedBy = "touristSpot")
    private List<Recommend> recommendList = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<Route> routeList = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<TouristSpotPurpose> touristSpotPurposeList = new ArrayList<>();

    @OneToMany(mappedBy = "touristSpot")
    private List<TouristSpotTheme> touristSpotThemeList = new ArrayList<>();
}
