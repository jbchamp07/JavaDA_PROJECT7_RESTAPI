package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RuleNameServiceTest {

    @Autowired
    private RuleNameService ruleNameService;

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
        ruleNameService.create(newRuleName);
        assertEquals("jsonTest2",ruleNameService.getById(5555).getJson());
        ruleNameService.delete(5555);
    }

    @Test
    public void updateTest(){
        ruleName.setJson("jsonTest2");
        ruleNameService.update(ruleName);
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
        ruleNameService.create(newRuleName);
        assertEquals("jsonTest2",ruleNameService.getById(5555).getJson());
        ruleNameService.delete(5555);
        assertEquals(null,ruleNameService.getById(5555));
    }

    @Test
    public void getByIdTest(){
        assertEquals("jsonTest",ruleNameService.getById(2555).getJson());
    }

    @Test
    public void updateBidListTest(){
        ruleName.setJson("jsonTest2");
        ruleNameService.updateRuleName(2555,ruleName);
        assertEquals("jsonTest2",ruleNameService.getById(2555).getJson());
    }

}
