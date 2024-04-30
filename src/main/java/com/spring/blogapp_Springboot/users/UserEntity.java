package com.spring.blogapp_Springboot.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column( nullable = false)
    @NonNull
    private String username;
    @Column( nullable = false)
    @NonNull
    private String password;

    @Column( nullable = false)
    @NonNull
    private String email;

    @Column( nullable = true)
    @Nullable
    private String bio;

    @Column( nullable = true)
    @Nullable
    private String image;

}
