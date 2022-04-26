package com.nnk.springboot.service;

import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

}
