package org.tukcapstone.jetsetgo.domain.shareGroup.converter;

import org.springframework.stereotype.Component;
import org.tukcapstone.jetsetgo.domain.shareGroup.entity.ShareGroup;
import org.tukcapstone.jetsetgo.domain.shareGroup.entity.enums.GroupType;

@Component
public class ShareGroupConverter {
    public ShareGroup toEntity(Integer groupSize) {
        return ShareGroup.builder()
                .maxMemberCount(groupSize)
                .currentMemberCount(1) // 그룹 생성 시 첫 멤버로 설정
                .groupType(GroupType.GROUP) // 개인 그룹 여부 설정
                .build();
    }

    public ShareGroup toEntity() {
        return ShareGroup.builder()
                .maxMemberCount(1)
                .currentMemberCount(1)
                .groupType(GroupType.INDIVIDUAL)
                .build();
    }
}

