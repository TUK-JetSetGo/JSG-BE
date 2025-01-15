package com.tukorea.jetsetgo.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "01. 회원 API", description = "회원 도메인의 API입니다.")
@RequiredArgsConstructor
public class UserController {
}
