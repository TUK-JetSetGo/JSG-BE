package org.tukcapstone.jetsetgo.domain.recommendation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.recommendation.domain.data.RecommendationId;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;
import org.tukcapstone.jetsetgo.domain.user.entity.User;

@Entity
@Table(name = "recommendation")
@Getter
@Setter
@NoArgsConstructor
public class Recommendation {

    @EmbeddedId
    private RecommendationId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("touristSpotId")
    @JoinColumn(name = "tourist_spot_id")
    private TouristSpot touristSpot;

    @Column(name = "recommended_at", updatable = false)
    private LocalDateTime recommendedAt;

    @PrePersist
    protected void onCreate() {
        this.recommendedAt = LocalDateTime.now();
    }
}
