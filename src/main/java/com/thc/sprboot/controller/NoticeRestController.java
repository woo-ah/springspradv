package com.thc.sprboot.controller;

import com.thc.sprboot.domain.Notice;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.NoticeDto;
import com.thc.sprboot.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/notice")
@RestController
public class NoticeRestController {

    private final NoticeService noticeService;
    public NoticeRestController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("")
    public ResponseEntity<NoticeDto.CreateResDto> create(@RequestBody NoticeDto.CreateReqDto params, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String test = (String) request.getAttribute("test");
        System.out.println("test :" + test);

        request.setAttribute("afterCtrl", "create!!");
        System.out.println("afterCtrl :" + request.getAttribute("afterCtrl"));

        response.setHeader("token1", "112233");

        //return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.create(params));
        return ResponseEntity.ok(noticeService.create(params));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody NoticeDto.UpdateReqDto params) {
        noticeService.update(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody NoticeDto.UpdateReqDto params) {
        noticeService.delete(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("")
    public ResponseEntity<NoticeDto.DetailResDto> detail(DefaultDto.DetailReqDto params) {
        return ResponseEntity.ok(noticeService.detail(params));
    }
    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto.DetailResDto>> list(NoticeDto.ListReqDto params) {
        return ResponseEntity.ok(noticeService.list(params));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(NoticeDto.PagedListReqDto params) {
        return ResponseEntity.ok(noticeService.pagedList(params));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<NoticeDto.DetailResDto>> scrollList(NoticeDto.ScrollListReqDto params) {
        return ResponseEntity.ok(noticeService.scrollList(params));
    }
}
