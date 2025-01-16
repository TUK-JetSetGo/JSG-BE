package org.tukcapstone.jetsetgo.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "01. 회원 API", description = "회원 도메인의 API입니다.")
@RequiredArgsConstructor
public class UserController {

    @Operation(summary = "Swagger 테스트", description = "Swagger 문서 테스트를 위한 간단한 API입니다.")
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("회원 API 테스트입니다.");
    }
}