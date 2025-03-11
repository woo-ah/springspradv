package com.thc.sprboot.dto;

import com.thc.sprboot.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class UserDto {


    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class LoginReqDto{
        private String username;
        private String password;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class LoginResDto{
        private String refreshToken;
    }

    /**/

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class CreateReqDto{
        private String username;
        private String password;
        private String name;
        private String nick;
        private String phone;

        public User toEntity(){
            return User.of(getUsername(), getPassword(), getName(), getNick(), getPhone());
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class CreateResDto{
        private Long id;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        private String password;
        private String name;
        private String nick;
        private String phone;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        private String username;
        private String name;
        private String nick;
        private String phone;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class ListReqDto extends DefaultDto.ListReqDto{
        private String nick;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        private String nick;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Getter
    @Setter
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        private String nick;
    }

}
