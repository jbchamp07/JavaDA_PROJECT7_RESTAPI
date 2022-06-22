package com.nnk.springboot.service;

import com.nnk.springboot.Application;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    private User user;

    @Test
    public void testContext(){

        userService.read();

    }

    @Before
    public void setUp(){
        user = new User();
        user.setId(2555);
        user.setUsername("usernameTest");
        user.setPassword("passwordTest");
        MockitoAnnotations.openMocks(this);
    }
    @After
    public void end(){
    }
    @BeforeEach
    public void create(){
        userService.create(user);
    }
    @AfterEach
    public void delete(){
        userService.delete(2555);
    }

    @Test
    public void readTest(){
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user);
        when(userRepository.findAll()).thenReturn(listUsers);
        List<User> list = userService.read();
        assertEquals("usernameTest",list.get(list.size() - 1).getUsername());
    }

    @Test
    public void createTest(){
        User newUser = new User();
        newUser.setUsername("uTest");
        newUser.setPassword("pTest");
        newUser.setId(5555);
        when(userRepository.save(newUser)).thenReturn(newUser);
        userService.create(newUser);
        when(userRepository.findByUsername("uTest")).thenReturn(Optional.of(newUser));
        assertEquals(true,userService.findByName("uTest"));
        //userService.delete(5555);
    }

    @Test
    public void updateTest(){
        user.setFullname("usernameTest2");
        when(userRepository.save(user)).thenReturn(user);
        userService.update(user);
        when(userRepository.findByUsername("usernameTest2")).thenReturn(Optional.of(user));
        assertEquals(true,userService.findByName("usernameTest2"));
    }

    @Test
    public void deleteTest(){
        User newUser = new User();
        newUser.setUsername("uTest");
        newUser.setPassword("pTest");
        newUser.setId(5555);

        final User entity = newUser;
        Optional<User> optionalEntityType = Optional.of(entity);
        Mockito.when(userRepository.findById(5555)).thenReturn(optionalEntityType);
        userService.delete(5555);
        Mockito.verify(userRepository, times(1)).deleteById(entity.getId());
    }



}
