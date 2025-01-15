package org.tukcapstone.jetsetgo.domain.group.enitty;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.group.enitty.data.UserGroupId;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.group.entity.Group;

@Entity
@Table(name = "user_group")
@Getter
@Setter
@NoArgsConstructor
public class UserGroup {

    @EmbeddedId
    private UserGroupId id;

    @Column(name = "joined_at", updatable = false)
    private LocalDateTime joinedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // UserGroupId의 userId 필드와 매핑됨
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupId")
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @PrePersist
    protected void onCreate() {
        this.joinedAt = LocalDateTime.now();
    }
}
