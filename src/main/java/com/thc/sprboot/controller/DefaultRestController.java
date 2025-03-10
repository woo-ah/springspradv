package com.thc.sprboot.controller;

import com.thc.sprboot.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/default")
@RestController
public class DefaultRestController {

    private final FileUpload fileUpload;
    public DefaultRestController(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        System.out.println("file : " + file.getOriginalFilename());

        String url = null;
        try {
            url = fileUpload.local(file, request);
        } catch (IOException e) {
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}
