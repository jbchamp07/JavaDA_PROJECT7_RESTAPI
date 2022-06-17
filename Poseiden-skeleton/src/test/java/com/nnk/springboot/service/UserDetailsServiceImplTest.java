package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp(){
        user = new User();
        user.setUsername("usernameTest");
        user.setFullname("fullnameTest");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsernameTestOk(){
        when(userRepository.findByUsername("usernameTest")).thenReturn(java.util.Optional.ofNullable(user));
        User userTest = userRepository.findByUsername("usernameTest").get();
        assertEquals("fullnameTest",userTest.getFullname());
    }
    @Test
    public void loadUserByUsernameTestNotOk(){
        when(userRepository.findByUsername("usernameTest2")).thenReturn(null);
        Optional<User> userTest = userRepository.findByUsername("usernameTest");
        assertEquals(false,userTest.isPresent());
    }
}
