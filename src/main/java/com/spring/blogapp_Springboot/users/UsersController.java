package com.spring.blogapp_Springboot.users;

import com.spring.blogapp_Springboot.Security.JWTService;
import com.spring.blogapp_Springboot.commons.dtos.ErrorResponses;
import com.spring.blogapp_Springboot.users.dtos.CreateUserRequest;
import com.spring.blogapp_Springboot.users.dtos.UserResponse;
import com.spring.blogapp_Springboot.users.dtos.LoginUserRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;
    public UsersController(UsersService usersService, ModelMapper modelMapper, JWTService jwtService) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest req){
        UserEntity savedUser = usersService.createUser(req);
        URI savedUserUri = URI.create("/users/" + savedUser.getId());
        var userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(jwtService.createJWT(savedUser.gerId()));
        return ResponseEntity.created(savedUserUri)
                .body(userResponse);
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest req){
        UserEntity savedUser = usersService.loginUser(req.getUsername(), req.getPassword());
        var userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(jwtService.createJWT(savedUser.gerId()));

        return ResponseEntity.ok(userResponse));
    }

    @ExceptionHandler({UsersService.UserNotFoundException.class, UsersService.InvalidCredentialsException.class})
    ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception e){
        String message;
        HttpStatus status;
        if(e instanceof UsersService.UserNotFoundException){
           message = e.getMessage();
           status = HttpStatus.NOT_FOUND;
        }else if(e instanceof UsersService.InvalidCredentialsException){
            message = e.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        } else{
            message = "Something went Wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse response = ErrorResponses.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
