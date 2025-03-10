package com.thc.sprboot.domain;

import com.thc.sprboot.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/*
 */
@Getter
@Entity
public class User extends AuditingFields{
    @Setter @Column(nullable = false, unique = true) String username;
    @Setter @Column(nullable = false) String password;
    @Setter String name;
    @Setter String nick;
    @Setter String phone;

    protected User(){}
    private User(Boolean deleted, String process, String username, String password, String name, String nick, String phone) {
        this.deleted = deleted;
        this.process = process;
        this.username = username;
        this.password = password;
        this.name = name;
        this.nick = nick;
        this.phone = phone;
    }
    public static User of(String username, String password, String name, String nick, String phone) {
        return new User(false, "", username, password, name, nick, phone);
    }

    public UserDto.CreateResDto toCreateResDto() {
        return UserDto.CreateResDto.builder().id(getId()).build();
    }
}
