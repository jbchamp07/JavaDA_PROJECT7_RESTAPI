package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(BidListService.class);
    @Autowired
    private UserRepository userRepository;

    public List<User> read(){
        return  userRepository.findAll();
    }

    public void create(User user){
        userRepository.save(user);
        logger.info("User : " + user + " is created");
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void delete(int userId){
        userRepository.deleteById(userId);
        logger.info("User with id " + userId + " is deleted");
    }

    public Boolean findByName(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if(user.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
