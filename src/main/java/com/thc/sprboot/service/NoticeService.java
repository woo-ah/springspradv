package com.thc.sprboot.service;

import com.thc.sprboot.domain.Notice;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.NoticeDto;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    NoticeDto.CreateResDto create(NoticeDto.CreateReqDto params);
    void update(NoticeDto.UpdateReqDto params);
    void delete(NoticeDto.UpdateReqDto params);
    NoticeDto.DetailResDto detail(DefaultDto.DetailReqDto params);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto params);

    DefaultDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto params);
    List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto params);
}
