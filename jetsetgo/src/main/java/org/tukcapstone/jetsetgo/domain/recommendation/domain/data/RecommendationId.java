package org.tukcapstone.jetsetgo.domain.recommendation.domain.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;

@Data
@Embeddable
public class RecommendationId implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "tourist_spot_id", nullable = false)
    private Long touristSpotId;
}