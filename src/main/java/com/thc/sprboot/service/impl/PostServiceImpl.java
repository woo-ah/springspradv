package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Post;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.PostDto;
import com.thc.sprboot.dto.PostimgDto;
import com.thc.sprboot.mapper.PostMapper;
import com.thc.sprboot.repository.PostRepository;
import com.thc.sprboot.service.PostService;
import com.thc.sprboot.service.PostimgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final PostimgService postimgService;
    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, PostimgService postimgService){
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.postimgService = postimgService;
    }

    @Override
    public PostDto.CreateResDto create(PostDto.CreateReqDto params) {
        PostDto.CreateResDto res = postRepository.save(params.toEntity()).toCreateResDto();
        List<String> imgs = params.getImgs();
        System.out.println("imgs : " + imgs.size());

        for(String each : imgs){
            postimgService.create(PostimgDto.CreateReqDto.builder().postId(res.getId()).url(each).build());
        }

        return res;
    }

    @Override
    public void update(PostDto.UpdateReqDto params) {
        Post post = postRepository.findById(params.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(params.getDeleted() != null){ post.setDeleted(params.getDeleted()); }
        if(params.getProcess() != null){ post.setProcess(params.getProcess()); }
        if(params.getTitle() != null){ post.setTitle(params.getTitle()); }
        if(params.getContent() != null){ post.setContent(params.getContent()); }
        postRepository.save(post);
    }

    @Override
    public void delete(PostDto.UpdateReqDto params) {
        params.setDeleted(true);
        update(params);
    }

    public PostDto.DetailResDto get(DefaultDto.DetailReqDto params) {
        PostDto.DetailResDto res = postMapper.detail(params);
        List<PostimgDto.DetailResDto> imgs = postimgService.list(PostimgDto.ListReqDto.builder().deleted(false).postId(res.getId()).build());
        res.setImgs(imgs);

        return res;
    }

    @Override
    public PostDto.DetailResDto detail(DefaultDto.DetailReqDto params) {
        return get(params);
    }

    public List<PostDto.DetailResDto> addlist(List<PostDto.DetailResDto> list) {
        List<PostDto.DetailResDto> finalList = new ArrayList<>();
        for(PostDto.DetailResDto each : list){
            finalList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return finalList;
    }

    @Override
    public List<PostDto.DetailResDto> list(PostDto.ListReqDto params) {
        params.init();
        return addlist(postMapper.list(params));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PostDto.PagedListReqDto params) {
        DefaultDto.PagedListResDto returnVal = params.init(postMapper.pagedListCount(params));
        returnVal.setList(addlist(postMapper.pagedList(params)));
        return returnVal;
    }
    @Override
    public List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto params) {
        params.init();
        return addlist(postMapper.scrollList(params));
    }
}
