package com.spring.blogapp_Springboot.users.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String image;
    private String token;
}
