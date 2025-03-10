package com.thc.sprboot.controller;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.PostDto;
import com.thc.sprboot.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/post")
@RestController
public class PostRestController {

    private final PostService postService;
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public ResponseEntity<PostDto.CreateResDto> create(@RequestBody PostDto.CreateReqDto params) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(params));
        return ResponseEntity.ok(postService.create(params));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostDto.UpdateReqDto params) {
        postService.update(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostDto.UpdateReqDto params) {
        postService.delete(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("")
    public ResponseEntity<PostDto.DetailResDto> detail(DefaultDto.DetailReqDto params) {
        return ResponseEntity.ok(postService.detail(params));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostDto.DetailResDto>> list(PostDto.ListReqDto params) {
        return ResponseEntity.ok(postService.list(params));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PostDto.PagedListReqDto params) {
        return ResponseEntity.ok(postService.pagedList(params));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<PostDto.DetailResDto>> scrollList(PostDto.ScrollListReqDto params) {
        return ResponseEntity.ok(postService.scrollList(params));
    }
}
