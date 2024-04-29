package com.spring.blogapp_Springboot.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentsRepository commentsRepository;
    public CommentService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}
