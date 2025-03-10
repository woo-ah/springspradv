package com.thc.sprboot.controller;

import com.thc.sprboot.domain.User;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.UserDto;
import com.thc.sprboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/user")
@RestController
public class UserRestController {

    private final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto.CreateResDto> login(@RequestBody UserDto.LoginReqDto params) {
        return ResponseEntity.ok(userService.login(params));
    }

    /**/

    @PostMapping("")
    public ResponseEntity<UserDto.CreateResDto> create(@RequestBody UserDto.CreateReqDto params) {
        return ResponseEntity.ok(userService.create(params));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody UserDto.UpdateReqDto params) {
        userService.update(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody UserDto.UpdateReqDto params) {
        userService.delete(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("")
    public ResponseEntity<UserDto.DetailResDto> detail(DefaultDto.DetailReqDto params) {
        return ResponseEntity.ok(userService.detail(params));
    }
    @GetMapping("/list")
    public ResponseEntity<List<UserDto.DetailResDto>> list(UserDto.ListReqDto params) {
        return ResponseEntity.ok(userService.list(params));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(UserDto.PagedListReqDto params) {
        return ResponseEntity.ok(userService.pagedList(params));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<UserDto.DetailResDto>> scrollList(UserDto.ScrollListReqDto params) {
        return ResponseEntity.ok(userService.scrollList(params));
    }
}
