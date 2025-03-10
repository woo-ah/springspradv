package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Post;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class PostDto {

    @NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
    public static class CreateReqDto{
        private Long userId;
        private String title;
        private String content;
        private List<String> imgs;

        public Post toEntity(){
            return Post.of(getUserId(), getTitle(), getContent());
        }
    }

    @NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
    public static class CreateResDto{
        private Long id;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        private String title;
        private String content;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        private Long userId;

        private String title;
        private String content;
        private String userNick;

        private List<PostimgDto.DetailResDto> imgs;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class ListReqDto extends DefaultDto.ListReqDto{
        private String title;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        private String title;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        private String title;
    }

}
