package com.spring.blogapp_Springboot.users;

import com.spring.blogapp_Springboot.users.dtos.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity createUser(CreateUserRequest req){
        return usersRepository.save(UserEntity.builder()
               .username(req.getUsername())
            //    .password(req.getPassword()) //TODO
               .email(req.getEmail())
                .build());
    }
    public UserEntity getUser(String username){
        return usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public UserEntity getUser(Long userId){
        return usersRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password){
        //TODO check password
        return usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username) {
            super("User with username: " + username + " not found");
        }
        public UserNotFoundException(Long userID) {
            super("User with id: " + userID + " not found");
        }
    }
}
