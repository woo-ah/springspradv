package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.PostimgDto;

import java.util.List;

public interface PostimgMapper {
    PostimgDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto params);

    int pagedListCount(PostimgDto.PagedListReqDto params);
    List<PostimgDto.DetailResDto> pagedList(PostimgDto.PagedListReqDto params);
    List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto params);
}
