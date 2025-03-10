package com.thc.sprboot.domain;

import com.thc.sprboot.dto.PostimgDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/*
*/
@Getter
@Entity
public class Postimg extends AuditingFields{
    @Setter Long postId;
    @Setter String url;

    protected Postimg(){}
    private Postimg(Boolean deleted, String process, Long postId, String url) {
        this.deleted = deleted;
        this.process = process;
        this.postId = postId;
        this.url = url;
    }
    public static Postimg of(Long postId, String url) {
        return new Postimg(false, "", postId, url);
    }

    public PostimgDto.CreateResDto toCreateResDto() {
        return PostimgDto.CreateResDto.builder().id(getId()).build();
    }
}
