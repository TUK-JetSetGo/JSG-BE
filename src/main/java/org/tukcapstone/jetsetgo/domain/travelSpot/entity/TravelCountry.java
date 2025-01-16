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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "travel_countries")
@Getter
@Setter
@NoArgsConstructor
public class TravelCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Long id;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private List<TravelCity> cities = new ArrayList<>();
}
