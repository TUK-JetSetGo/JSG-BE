package org.tukcapstone.jetsetgo.domain.group.enitty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tukcapstone.jetsetgo.domain.travelPlan.entity.TravelPlan;

@Entity
@Table(name = "`groups`")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long id;

    @Column(name = "max_member_count")
    private Integer maxMemberCount;

    @Column(name = "current_member_count")
    private Integer currentMemberCount;

    @Column(name = "is_personal")
    private Boolean isPersonal;

    @OneToMany(mappedBy = "group")
    private List<TravelPlan> travelPlans = new ArrayList<>();

}
