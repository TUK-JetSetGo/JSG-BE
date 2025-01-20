package org.tukcapstone.jetsetgo.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.tukcapstone.jetsetgo.domain.group.enitty.UserGroup;
import org.tukcapstone.jetsetgo.domain.recommend.entity.Recommend;
import org.tukcapstone.jetsetgo.domain.review.entity.Review;
import org.tukcapstone.jetsetgo.domain.user.entity.enums.SocialType;
import org.tukcapstone.jetsetgo.global.entity.BaseTimeEntity;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialType;

    @Column(nullable = false)
    private String providerId;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserGroup> userGroupList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Recommend> recommendList = new ArrayList<>();
}
