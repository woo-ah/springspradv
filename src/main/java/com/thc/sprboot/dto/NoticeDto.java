package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Notice;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class NoticeDto {

    @NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter
    public static class CreateReqDto{
        private String title;
        private String content;

        public Notice toEntity(){
            return Notice.of(getTitle(), getContent());
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
        private String title;
        private String content;
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
