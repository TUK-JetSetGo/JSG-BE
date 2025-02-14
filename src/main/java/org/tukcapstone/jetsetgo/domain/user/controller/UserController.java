package org.tukcapstone.jetsetgo.domain.user.controller;

import static org.tukcapstone.jetsetgo.global.response.exception.code.AuthErrorCode.UNAUTHORIZED;
import static org.tukcapstone.jetsetgo.global.response.result.code.AuthResultCode.AUTHORIZED;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tukcapstone.jetsetgo.domain.user.dto.UserResponse;
import org.tukcapstone.jetsetgo.domain.user.dto.UserResponse.UserProfile;
import org.tukcapstone.jetsetgo.domain.user.entity.User;
import org.tukcapstone.jetsetgo.domain.user.service.UserService;
import org.tukcapstone.jetsetgo.global.response.ResultResponse;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/profile")
    public ResultResponse<UserProfile> getProfile(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResultResponse.onFailure(UNAUTHORIZED);
        }
        UserResponse.UserProfile profile =  userService.getUserProfileById(user.getId());
        return ResultResponse.onSuccess(AUTHORIZED, profile);
    }

    @PutMapping("/profile")
    public ResultResponse<String> updateProfile(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResultResponse.onFailure(UNAUTHORIZED, "인증되지 않은 사용자입니다.");
        }
        return ResultResponse.onSuccess(AUTHORIZED, "환영합니다, " + user.getName() + "님!");
    }

}
