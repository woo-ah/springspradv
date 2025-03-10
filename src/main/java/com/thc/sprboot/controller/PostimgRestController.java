package com.thc.sprboot.controller;

import com.thc.sprboot.domain.Postimg;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.PostimgDto;
import com.thc.sprboot.service.PostimgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/postimg")
@RestController
public class PostimgRestController {

    private final PostimgService postimgService;
    public PostimgRestController(PostimgService postimgService) {
        this.postimgService = postimgService;
    }

    @PostMapping("")
    public ResponseEntity<PostimgDto.CreateResDto> create(@RequestBody PostimgDto.CreateReqDto params) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(postimgService.create(params));
        return ResponseEntity.ok(postimgService.create(params));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostimgDto.UpdateReqDto params) {
        postimgService.update(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostimgDto.UpdateReqDto params) {
        postimgService.delete(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("")
    public ResponseEntity<PostimgDto.DetailResDto> detail(DefaultDto.DetailReqDto params) {
        return ResponseEntity.ok(postimgService.detail(params));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostimgDto.DetailResDto>> list(PostimgDto.ListReqDto params) {
        return ResponseEntity.ok(postimgService.list(params));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PostimgDto.PagedListReqDto params) {
        return ResponseEntity.ok(postimgService.pagedList(params));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<PostimgDto.DetailResDto>> scrollList(PostimgDto.ScrollListReqDto params) {
        return ResponseEntity.ok(postimgService.scrollList(params));
    }
}
