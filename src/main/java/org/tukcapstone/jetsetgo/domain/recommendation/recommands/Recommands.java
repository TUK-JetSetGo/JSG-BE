package org.tukcapstone.jetsetgo.domain.recommendation.recommands;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.touristSpot.entity.TouristSpot;
import org.tukcapstone.jetsetgo.domain.user.entity.User;

@Entity
@Table(name = "recommands")
@Getter
@Setter
@NoArgsConstructor
public class Recommands {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id")
    private TouristSpot touristSpot;
}
