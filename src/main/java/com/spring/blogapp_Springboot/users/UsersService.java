package com.spring.blogapp_Springboot.users;

import com.spring.blogapp_Springboot.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserEntity createUser(CreateUserRequest req){
        UserEntity newUser = modelMapper.map(req, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode( req.getPassword()) );
        return usersRepository.save(newUser);
    }
    public UserEntity getUser(String username){
        return usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public UserEntity getUser(Long userId){
        return usersRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password){
        var user = usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
        var passMatch = passwordEncoder.matches(password, user.getPassword());
        if(!passMatch) throw new InvalidCredentialsException(username);
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username) {
            super("User with username: " + username + " not found");
        }
        public UserNotFoundException(Long userID) {
            super("User with id: " + userID + " not found");
        }
    }
    public static class InvalidCredentialsException extends IllegalArgumentException{
        public InvalidCredentialsException(String username) {
            super("Invalid with username or password combination");
        }
    }
}
