package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RuleNameServiceTest {

    @InjectMocks
    private RuleNameService ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    private RuleName ruleName;

    @Before
    public void setUp(){
        ruleName = new RuleName();
        ruleName.setId(2555);
        ruleName.setDescription("descriptionTest");
        ruleName.setJson("jsonTest");
        ruleName.setTemplate("templateTest");
        ruleName.setSqlStr("sqlStrTest");
        ruleName.setSqlPart("sqlPartTest");
        MockitoAnnotations.openMocks(this);
    }
    @After
    public void end(){
        //ruleNameService.delete(2555);
    }
    @BeforeEach
    public void create(){
        ruleNameService.create(ruleName);
    }
    @AfterEach
    public void delete(){
        ruleNameService.delete(2555);
    }

    @Test
    public void readTest() {
        List<RuleName> listRuleName = new ArrayList<>();
        listRuleName.add(ruleName);
        when(ruleNameRepository.findAll()).thenReturn(listRuleName);
        List<RuleName> list = ruleNameService.read();
        assertEquals("jsonTest",list.get(list.size() - 1).getJson());
    }

    @Test
    public void createTest(){
        RuleName newRuleName = new RuleName();
        newRuleName.setId(5555);
        newRuleName.setDescription("descriptionTest2");
        newRuleName.setJson("jsonTest2");
        newRuleName.setTemplate("templateTest2");
        newRuleName.setSqlStr("sqlStrTest2");
        newRuleName.setSqlPart("sqlPartTest2");
        when(ruleNameRepository.save(newRuleName)).thenReturn(newRuleName);
        ruleNameService.create(newRuleName);
        when(ruleNameRepository.findById(5555)).thenReturn(Optional.of(newRuleName));
        assertEquals("jsonTest2",ruleNameService.getById(5555).getJson());
        ruleNameService.delete(5555);
    }

    @Test
    public void updateTest(){
        ruleName.setJson("jsonTest2");
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        ruleNameService.update(ruleName);
        when(ruleNameRepository.findById(2555)).thenReturn(Optional.ofNullable(ruleName));
        assertEquals("jsonTest2",ruleNameService.getById(2555).getJson());
    }

    @Test
    public void deleteTest(){
        RuleName newRuleName = new RuleName();
        newRuleName.setId(5555);
        newRuleName.setDescription("descriptionTest2");
        newRuleName.setJson("jsonTest2");
        newRuleName.setTemplate("templateTest2");
        newRuleName.setSqlStr("sqlStrTest2");
        newRuleName.setSqlPart("sqlPartTest2");

        final RuleName entity = newRuleName;
        Optional<RuleName> optionalEntityType = Optional.of(entity);
        Mockito.when(ruleNameRepository.findById(5555)).thenReturn(optionalEntityType);
        ruleNameService.delete(5555);
        Mockito.verify(ruleNameRepository, times(1)).deleteById(entity.getId());
    }

    @Test
    public void getByIdTest(){
        when(ruleNameRepository.findById(2555)).thenReturn(Optional.ofNullable(ruleName));
        assertEquals("jsonTest",ruleNameService.getById(2555).getJson());
    }

    @Test
    public void updateBidListTest(){
        ruleName.setJson("jsonTest2");
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        ruleNameService.updateRuleName(2555,ruleName);
        when(ruleNameRepository.findById(2555)).thenReturn(Optional.ofNullable(ruleName));
        assertEquals("jsonTest2",ruleNameService.getById(2555).getJson());
    }
    @Test
    public void updateBidListTestIf(){
        ruleName.setJson("jsonTest2");
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        when(ruleNameRepository.findById(2555)).thenReturn(Optional.ofNullable(ruleName));
        ruleNameService.updateRuleName(2555,ruleName);
        assertEquals("jsonTest2",ruleNameService.getById(2555).getJson());
    }
}
