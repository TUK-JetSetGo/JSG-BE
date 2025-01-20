package org.tukcapstone.jetsetgo.domain.travelSpot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "travel_countries")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TravelCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_country_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String countryName;

    @OneToMany(mappedBy = "travelCountry")
    private List<TravelCity> travelCityList = new ArrayList<>();
}
