package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {
    Logger logger = LoggerFactory.getLogger(RuleNameService.class);
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> read(){
        return  ruleNameRepository.findAll();
    }

    public void create(RuleName ruleName){
        ruleNameRepository.save(ruleName);
        logger.info("RuleName : " + ruleName + " is created");
    }

    public void update(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }

    public void delete(int ruleNameId){
        ruleNameRepository.deleteById(ruleNameId);
        logger.info("RuleName with id " + ruleNameId + " is deleted");
    }

    public RuleName getById(Integer id) {
        return ruleNameRepository.findById(id).get();
    }

    public Boolean updateRuleName(Integer id, RuleName ruleName) {
        boolean updated = false;
        Optional<RuleName> list = ruleNameRepository.findById(id);
        if (list.isPresent()) {
            RuleName newRuleName = list.get();
            newRuleName.setName(ruleName.getName());
            newRuleName.setSqlPart(ruleName.getSqlPart());
            newRuleName.setTemplate(ruleName.getTemplate());
            newRuleName.setJson(ruleName.getJson());
            newRuleName.setDescription(ruleName.getDescription());
            newRuleName.setSqlStr(ruleName.getSqlStr());
            ruleNameRepository.save(newRuleName);
            updated = true;
            logger.info("RuleName with id " + id + " is updated as " + newRuleName);
        } else {
            logger.error("Failed to update RuleName with id " + id + " as " + ruleName);
        }
        return updated;
    }

}
