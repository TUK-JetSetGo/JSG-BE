package org.tukcapstone.jetsetgo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;

public abstract class UserRequest {
    private UserRequest(){}

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserProfile {
        private JsonNullable<String> name = JsonNullable.undefined();
        private JsonNullable<String> email = JsonNullable.undefined();
    }
}
