package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {

    @InjectMocks
    private TradeService tradeService;
    @Mock
    private TradeRepository tradeRepository;

    private Trade trade;

    @Before
    public void setUp(){
        trade = new Trade();
        trade.setTradeId(2555);
        trade.setAccount("accountTest");
        MockitoAnnotations.openMocks(this);
    }
    @After
    public void end(){
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

        List<Trade> listTrade = new ArrayList<>();
        listTrade.add(trade);
        when(tradeRepository.findAll()).thenReturn(listTrade);
        List<Trade> list = tradeService.read();
        assertEquals("accountTest",list.get(list.size() - 1).getAccount());
    }

    @Test
    public void createTest(){
        Trade newTrade = new Trade();
        newTrade.setTradeId(5555);
        newTrade.setAccount("accountTest2");
        when(tradeRepository.save(newTrade)).thenReturn(newTrade);
        tradeService.create(newTrade);
        when(tradeRepository.findById(5555)).thenReturn(Optional.of(newTrade));
        assertEquals("accountTest2",tradeService.getById(5555).getAccount());
        //tradeService.delete(5555);

    }

    @Test
    public void updateTest(){
        trade.setAccount("accountTest2");
        when(tradeRepository.save(trade)).thenReturn(trade);
        tradeService.update(trade);
        when(tradeRepository.findById(2555)).thenReturn(Optional.ofNullable(trade));
        assertEquals("accountTest2",tradeService.getById(2555).getAccount());
    }

    //TODO
   /* @Test
    public void deleteTest(){
        Trade newTrade = new Trade();
        newTrade.setTradeId(5555);
        newTrade.setAccount("accountTest2");
        when(tradeRepository.save(newTrade)).thenReturn(newTrade);
        tradeService.create(newTrade);
        when(tradeRepository.findById(5555)).thenReturn(Optional.of(newTrade));
        assertEquals("accountTest2",tradeService.getById(5555).getAccount());
        tradeService.delete(5555);
        when(tradeRepository.findById(5555)).thenReturn(null);
        assertEquals(null,tradeService.getById(5555));
    }*/

    @Test
    public void getByIdTest(){
        when(tradeRepository.findById(2555)).thenReturn(Optional.ofNullable(trade));
        assertEquals("accountTest",tradeService.getById(2555).getAccount());
    }

    @Test
    public void updateBidListTest(){
        trade.setAccount("accountTest2");
        when(tradeRepository.save(trade)).thenReturn(trade);
        tradeService.updateTrade(2555,trade);
        when(tradeRepository.findById(2555)).thenReturn(Optional.ofNullable(trade));
        assertEquals("accountTest2",tradeService.getById(2555).getAccount());
    }

}
