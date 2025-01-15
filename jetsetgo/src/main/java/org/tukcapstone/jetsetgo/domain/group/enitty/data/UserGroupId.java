package org.tukcapstone.jetsetgo.domain.group.enitty.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Data
@Embeddable
public class UserGroupId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;
}