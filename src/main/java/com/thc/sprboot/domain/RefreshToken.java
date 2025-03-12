package com.thc.sprboot.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/*
*/
@Getter
@Entity
public class RefreshToken extends AuditingFields{
    @Setter Long userId;
    @Setter String content;

    protected RefreshToken(){}
    private RefreshToken(Boolean deleted, String process, Long userId, String content) {
        this.deleted = deleted;
        this.process = process;
        this.userId = userId;
        this.content = content;
    }
    public static RefreshToken of(Long userId, String content) {
        return new RefreshToken(false, "", userId, content);
    }
}
