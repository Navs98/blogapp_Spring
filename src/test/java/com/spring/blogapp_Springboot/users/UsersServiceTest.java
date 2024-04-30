//package com.spring.blogapp_Springboot.users;
//
//import com.spring.blogapp_Springboot.users.dtos.CreateUserRequest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class UsersServiceTest {
//    @Autowired
//    UsersService usersService;
//
//    @Test
//    void can_create_user(){
//        var user = usersService.createUser(new CreateUserRequest(
//                "Navs",
//                "pass123",
//                "Navs@gmail.com"
//        ));
//        Assertions.assertNotNull(user);
//        Assertions.assertEquals("Navs", user.getUsername());
//    }
//
//}
