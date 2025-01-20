package org.tukcapstone.jetsetgo.domain.group.enitty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tukcapstone.jetsetgo.global.entity.BaseTimeEntity;

@Entity
@Table(name = "share_groups")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ShareGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_group_id")
    private Long id;

    @Column(nullable = false)
    private Integer maxMemberCount;

    @Column(nullable = false)
    private Integer currentMemberCount;

    @Column(nullable = false)
    private Boolean isPersonal;
}
