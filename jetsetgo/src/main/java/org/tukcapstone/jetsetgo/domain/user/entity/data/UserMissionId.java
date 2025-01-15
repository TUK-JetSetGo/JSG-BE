package org.tukcapstone.jetsetgo.domain.user.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Data
@Embeddable
public class UserMissionId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "mission_id", nullable = false)
    private Long missionId;
}
