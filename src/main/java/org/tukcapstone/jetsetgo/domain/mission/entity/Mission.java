package org.tukcapstone.jetsetgo.domain.mission.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

@Entity
@Table(name = "missions")
@Getter
@Setter
@NoArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id", nullable = false)
    private TouristSpot touristSpot;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "reward_point", nullable = false)
    private Integer rewardPoint;
}