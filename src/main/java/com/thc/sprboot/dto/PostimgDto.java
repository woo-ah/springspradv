package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Postimg;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PostimgDto {

    @NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
    public static class CreateReqDto{
        private Long postId;
        private String url;

        public Postimg toEntity(){
            return Postimg.of(getPostId(), getUrl());
        }
    }

    @NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
    public static class CreateResDto{
        private Long id;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        private String url;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        private Long postId;
        private String url;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class ListReqDto extends DefaultDto.ListReqDto{
        private Long postId;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        private Long postId;
    }

    @NoArgsConstructor @AllArgsConstructor @SuperBuilder @Getter @Setter
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        private Long postId;
    }

}
