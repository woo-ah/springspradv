package com.thc.sprboot.controller;

import com.thc.sprboot.dto.NoticeDto;
import com.thc.sprboot.service.NoticeService;
import com.thc.sprboot.util.FileUpload;
import com.thc.sprboot.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//RefreshToken을 이용해서 AccessToken을 새로 발급
// 사용자가 로그인 후 AccessToken이 만료됐을 때, RefreshToken을 이용해서 다시 AccessToken을 받아 옴
@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    private final TokenFactory tokenFactory;
    public AuthRestController(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }


    @PostMapping("")
    public ResponseEntity<Void> accessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //RefreshToken 받아오기: 클라이언트가 보낸 RefreshToken을 HTTP 헤더에서 가져옴
        String refreshToken = request.getHeader("RefreshToken");
        System.out.println("refreshToken :" + refreshToken);

        Long userId = tokenFactory.verifyRefreshToken(refreshToken); //토큰이 유효한지 검사해서 사용자 ID를 꺼냄
        String accessToken = tokenFactory.generateAccessToken(userId); //사용자 ID로 새로운 AccessToken을 생성

        //HTTP 응답의 헤더에 AccessToken을 담아서 반환
        return ResponseEntity.ok().header("AccessToken",accessToken).build();
    }
}
