package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> read(){
        return  userRepository.findAll();
    }

    public void create(User user){
        userRepository.save(user);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void delete(int UserId){
        userRepository.deleteById(UserId);
    }

}
