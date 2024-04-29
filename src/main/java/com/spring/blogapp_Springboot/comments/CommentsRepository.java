package com.spring.blogapp_Springboot.comments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {
}
