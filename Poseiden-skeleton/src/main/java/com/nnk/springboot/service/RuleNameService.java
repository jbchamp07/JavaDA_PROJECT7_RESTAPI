package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> read(){
        return  ruleNameRepository.findAll();
    }

    public void create(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }

    public void update(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }

    public void delete(int RuleNameId){
        ruleNameRepository.deleteById(RuleNameId);
    }

}
