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

@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    private final TokenFactory tokenFactory;
    public AuthRestController(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }


    @PostMapping("")
    public ResponseEntity<Void> accessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String refreshToken = request.getHeader("RefreshToken");
        System.out.println("refreshToken :" + refreshToken);

        Long userId = tokenFactory.verifyRefreshToken(refreshToken);
        String accessToken = tokenFactory.generateAccessToken(userId);

        return ResponseEntity.ok().header("AccessToken",accessToken).build();
    }
}
