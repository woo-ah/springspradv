package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.User;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.UserDto;
import com.thc.sprboot.mapper.UserMapper;
import com.thc.sprboot.repository.UserRepository;
import com.thc.sprboot.service.UserService;
import com.thc.sprboot.util.TokenFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenFactory tokenFactory;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, TokenFactory tokenFactory){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public UserDto.LoginResDto login(UserDto.LoginReqDto params) {
        User user = userRepository.findByUsernameAndPassword(params.getUsername(), params.getPassword());
        String refreshToken = null;
        UserDto.LoginResDto resDto = null;
        if(user == null){ //// 사용자가 없으면 아무것도 안 하고 넘어감 (나중에 예외 처리 필요)
        } else {
            refreshToken = tokenFactory.generateRefreshToken(user.getId()); //사용자 ID를 이용해서 RefreshToken 발급
            /*String afterValue = tokenFactory.verifyToken(refreshToken);
            System.out.println("afterValue: " + afterValue);*/
        }
        return UserDto.LoginResDto.builder().refreshToken(refreshToken).build(); // refreshToken을 담은 LoginResDto 객체를 생성해서 반환
    }

//week1-2코드
//    @Override
//    public UserDto.LoginResDto login(UserDto.LoginReqDto params) {
//        User user = userRepository.findByUsernameAndPassword(params.getUsername(), params.getPassword());
//        String refreshToken = null;
//        UserDto.LoginResDto resDto = null;
//
//        if(user == null){
//            // 아이디가 없거나 비밀번호가 틀렸을 때
//            return UserDto.CreateResDto.builder().id((long)-100).build();
//        } else {
//            refreshToken = new TokenFactory().generateToken(user.getId()); // RefreshToken 생성
//            String afterValue = new TokenFactory().verifyToken(refreshToken); // 토큰 검증
//            System.out.println("afterValue: " + afterValue);
//        }
//
//        return UserDto.LoginResDto.builder().refreshToken(refreshToken).build();
//    }

    /**/

    @Override
    public UserDto.CreateResDto create(UserDto.CreateReqDto params) {

        User user = userRepository.findByUsername(params.getUsername());
        if(user != null){
            //아이디가 중복이라는 뜻!
            //throw new RuntimeException("id duplicated");
            return UserDto.CreateResDto.builder().id((long)-100).build();
        }
        return userRepository.save(params.toEntity()).toCreateResDto();
    }

    @Override
    public void update(UserDto.UpdateReqDto params) {
        User user = userRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(params.getDeleted() != null){ user.setDeleted(params.getDeleted()); }
        if(params.getProcess() != null){ user.setProcess(params.getProcess()); }

        if(params.getPassword() != null){ user.setPassword(params.getPassword()); }
        if(params.getName() != null){ user.setName(params.getName()); }
        if(params.getNick() != null){ user.setNick(params.getNick()); }
        if(params.getPhone() != null){ user.setPhone(params.getPhone()); }
        userRepository.save(user);
    }

    @Override
    public void delete(UserDto.UpdateReqDto params) {
        params.setDeleted(true);
        update(params);
    }

    public UserDto.DetailResDto get(DefaultDto.DetailReqDto params) {
        return userMapper.detail(params);
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto params) {
        return get(params);
    }

    public List<UserDto.DetailResDto> addlist(List<UserDto.DetailResDto> list) {
        List<UserDto.DetailResDto> finalList = new ArrayList<>();
        for(UserDto.DetailResDto each : list){
            finalList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return finalList;
    }

    @Override
    public List<UserDto.DetailResDto> list(UserDto.ListReqDto params) {
        params.init();
        return addlist(userMapper.list(params));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto params) {
        DefaultDto.PagedListResDto returnVal = params.init(userMapper.pagedListCount(params));
        returnVal.setList(addlist(userMapper.pagedList(params)));
        return returnVal;
    }
    @Override
    public List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto params) {
        params.init();
        return addlist(userMapper.scrollList(params));
    }
}
