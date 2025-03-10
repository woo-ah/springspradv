package com.thc.sprboot.service;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.PostimgDto;

import java.util.List;

public interface PostimgService {
    PostimgDto.CreateResDto create(PostimgDto.CreateReqDto params);
    void update(PostimgDto.UpdateReqDto params);
    void delete(PostimgDto.UpdateReqDto params);
    PostimgDto.DetailResDto detail(DefaultDto.DetailReqDto params);
    List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto params);

    DefaultDto.PagedListResDto pagedList(PostimgDto.PagedListReqDto params);
    List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto params);
}
