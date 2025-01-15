package org.tukcapstone.jetsetgo.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.mission.entity.Mission;
import org.tukcapstone.jetsetgo.domain.user.entity.data.UserMissionId;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.MissionSuccessStatus;

@Entity
@Table(name = "user_mission")
@Getter
@Setter
@NoArgsConstructor
public class UserMission {

    @EmbeddedId
    private UserMissionId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("missionId")
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "success", nullable = false)
    private MissionSuccessStatus success;

}