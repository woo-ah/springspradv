package com.thc.sprboot.domain;

import com.thc.sprboot.dto.PostDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/*
*/
@Getter
@Entity
public class Post extends AuditingFields{
    @Setter Long userId;

    @Setter String title;
    @Setter String content;

    protected Post(){}
    private Post(Boolean deleted, String process, Long userId, String title, String content) {
        this.deleted = deleted;
        this.process = process;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    public static Post of(Long userId, String title, String content) {
        return new Post(false, "", userId, title, content);
    }

    public PostDto.CreateResDto toCreateResDto() {
        return PostDto.CreateResDto.builder().id(getId()).build();
    }
}
