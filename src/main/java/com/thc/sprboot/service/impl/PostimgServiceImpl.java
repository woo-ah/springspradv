package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Postimg;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.PostimgDto;
import com.thc.sprboot.mapper.PostimgMapper;
import com.thc.sprboot.repository.PostimgRepository;
import com.thc.sprboot.service.PostimgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostimgServiceImpl implements PostimgService {

    private final PostimgRepository postimgRepository;
    private final PostimgMapper postimgMapper;
    public PostimgServiceImpl(PostimgRepository postimgRepository, PostimgMapper postimgMapper){
        this.postimgRepository = postimgRepository;
        this.postimgMapper = postimgMapper;
    }

    @Override
    public PostimgDto.CreateResDto create(PostimgDto.CreateReqDto params) {
        return postimgRepository.save(params.toEntity()).toCreateResDto();
    }

    @Override
    public void update(PostimgDto.UpdateReqDto params) {
        Postimg postimg = postimgRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(params.getDeleted() != null){ postimg.setDeleted(params.getDeleted()); }
        if(params.getProcess() != null){ postimg.setProcess(params.getProcess()); }
        postimgRepository.save(postimg);
    }

    @Override
    public void delete(PostimgDto.UpdateReqDto params) {
        params.setDeleted(true);
        update(params);
    }

    public PostimgDto.DetailResDto get(DefaultDto.DetailReqDto params) {
        return postimgMapper.detail(params);
    }

    @Override
    public PostimgDto.DetailResDto detail(DefaultDto.DetailReqDto params) {
        return get(params);
    }

    public List<PostimgDto.DetailResDto> addlist(List<PostimgDto.DetailResDto> list) {
        List<PostimgDto.DetailResDto> finalList = new ArrayList<>();
        for(PostimgDto.DetailResDto each : list){
            finalList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return finalList;
    }

    @Override
    public List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto params) {
        params.init();
        return addlist(postimgMapper.list(params));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PostimgDto.PagedListReqDto params) {
        DefaultDto.PagedListResDto returnVal = params.init(postimgMapper.pagedListCount(params));
        returnVal.setList(addlist(postimgMapper.pagedList(params)));
        return returnVal;
    }
    @Override
    public List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto params) {
        params.init();
        return addlist(postimgMapper.scrollList(params));
    }
}
