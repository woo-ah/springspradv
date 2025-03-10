package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.UserDto;

import java.util.List;

public interface UserMapper {
    UserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto params);

    int pagedListCount(UserDto.PagedListReqDto params);
    List<UserDto.DetailResDto> pagedList(UserDto.PagedListReqDto params);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto params);
}
