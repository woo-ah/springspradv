package com.thc.sprboot.service;

import com.thc.sprboot.domain.User;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto.CreateResDto login(UserDto.LoginReqDto params);
    /**/
    UserDto.CreateResDto create(UserDto.CreateReqDto params);
    void update(UserDto.UpdateReqDto params);
    void delete(UserDto.UpdateReqDto params);
    UserDto.DetailResDto detail(DefaultDto.DetailReqDto params);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto params);

    DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto params);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto params);
}
