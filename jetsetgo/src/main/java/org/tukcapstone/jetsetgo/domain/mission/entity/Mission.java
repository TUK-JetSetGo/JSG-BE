package org.tukcapstone.jetsetgo.domain.mission.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;

@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor
public class Mission {

    @Id
    @Column(name = "mission_id", nullable = false)
    private Long missionId;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id", nullable = false)
    private TouristSpot touristSpot;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "reward_point", nullable = false)
    private Integer rewardPoint;
}