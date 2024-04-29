package com.spring.blogapp_Springboot.articles.dtos;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {
    @NonNull
    private String title;
    @NonNull
    private String body;
    @NonNull
    private String subtitle;
}
