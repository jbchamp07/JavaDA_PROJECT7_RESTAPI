package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private User user;

    @Before
    public void setUp(){
        user = new User();
        user.setId(2555);
        user.setUsername("usernameTest");
        user.setPassword("passwordTest");
    }
    @After
    public void end(){
        //bidListService.delete(2555);
    }
    @BeforeEach
    public void create(){
        //createTest();
        userService.create(user);
    }
    @AfterEach
    public void delete(){
        //deleteTest();
        userService.delete(2555);
    }

    @Test
    public void readTest(){
        List<User> list = userService.read();
        assertEquals("usernameTest",list.get(list.size() - 1).getUsername());
    }

    @Test
    public void createTest(){
        User newUser = new User();
        newUser.setUsername("uTest");
        newUser.setPassword("pTest");
        newUser.setId(5555);
        userService.create(newUser);
        assertEquals(true,userService.findByName("uTest"));
        userService.delete(5555);
    }

    @Test
    public void updateTest(){
        user.setFullname("usernameTest2");
        userService.update(user);
        assertEquals("true",userService.findByName("usernameTest2"));
    }

    @Test
    public void deleteTest(){
        User newUser = new User();
        newUser.setUsername("uTest");
        newUser.setPassword("pTest");
        newUser.setId(5555);
        userService.create(newUser);
        assertEquals(true,userService.findByName("uTest"));
        userService.delete(5555);
        assertEquals(false,userService.findByName("uTest"));
    }



}
