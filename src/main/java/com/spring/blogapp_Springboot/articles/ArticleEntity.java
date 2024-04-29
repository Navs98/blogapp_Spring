package com.spring.blogapp_Springboot.articles;

import com.spring.blogapp_Springboot.users.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    @NonNull
    private String title;

    @NonNull
    @Column(unique = true)
    private String slug;

    @Nullable
    private String subtitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private UserEntity author;
}
