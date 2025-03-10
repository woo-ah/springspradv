package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.PostDto;

import java.util.List;

public interface PostMapper {
    PostDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostDto.DetailResDto> list(PostDto.ListReqDto params);

    int pagedListCount(PostDto.PagedListReqDto params);
    List<PostDto.DetailResDto> pagedList(PostDto.PagedListReqDto params);
    List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto params);
}
