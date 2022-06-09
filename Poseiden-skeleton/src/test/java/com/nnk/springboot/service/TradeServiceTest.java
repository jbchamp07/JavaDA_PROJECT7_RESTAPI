package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
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
public class TradeServiceTest {

    @Autowired
    private TradeService tradeService;

    private Trade trade;

    @Before
    public void setUp(){
        trade = new Trade();
        trade.setTradeId(2555);
        trade.setAccount("accountTest");

    }
    @After
    public void end(){
        //ruleNameService.delete(2555);
    }
    @BeforeEach
    public void create(){
        tradeService.create(trade);
    }
    @AfterEach
    public void delete(){
        tradeService.delete(2555);
    }

    @Test
    public void readTest() {
        List<Trade> list = tradeService.read();
        assertEquals("accountTest",list.get(list.size() - 1).getAccount());
    }

    @Test
    public void createTest(){
        Trade newTrade = new Trade();
        newTrade.setTradeId(5555);
        newTrade.setAccount("accountTest2");
        tradeService.create(newTrade);
        assertEquals("accountTest2",tradeService.getById(5555).getAccount());
        tradeService.delete(5555);
    }

    @Test
    public void updateTest(){
        trade.setAccount("accountTest2");
        tradeService.update(trade);
        assertEquals("accountTest2",tradeService.getById(2555).getAccount());
    }

    @Test
    public void deleteTest(){
        Trade newTrade = new Trade();
        newTrade.setTradeId(5555);
        newTrade.setAccount("accountTest2");
        tradeService.create(newTrade);
        assertEquals("accountTest2",tradeService.getById(5555).getAccount());
        tradeService.delete(5555);
        assertEquals(null,tradeService.getById(5555));
    }

    @Test
    public void getByIdTest(){
        assertEquals("accountTest",tradeService.getById(2555).getAccount());
    }

    @Test
    public void updateBidListTest(){
        trade.setAccount("accountTest2");
        tradeService.updateTrade(2555,trade);
        assertEquals("accountTest2",tradeService.getById(2555).getAccount());
    }

}
