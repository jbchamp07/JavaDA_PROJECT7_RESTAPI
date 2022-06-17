package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BidListServiceTest {

    @InjectMocks
    private BidListService bidListService;
    @Mock
    private BidListRepository bidListRepository;

    private BidList bidList;

    @Before
    public void setUp(){
        bidList = new BidList();
        bidList.setAccount("accountTest");
        bidList.setBidListId(2555);
        bidList.setBidQuantity(2.555);
        bidList.setType("typeTest");
        MockitoAnnotations.openMocks(this);
    }
    @After
    public void end(){
        //bidListService.delete(2555);
    }
    @BeforeEach
    public void create(){
        //createTest();
        bidListService.create(bidList);
    }
    @AfterEach
    public void delete(){
        //deleteTest();
        bidListService.delete(2555);
    }

    @Test
    public void readTest() {
        List<BidList> listBidList = new ArrayList<>();
        listBidList.add(bidList);
        when(bidListRepository.findAll()).thenReturn(listBidList);
        List<BidList> list = bidListService.read();
        assertEquals("accountTest",list.get(list.size() - 1).getAccount());
    }

    @Test
    public void createTest(){
        BidList newBidList = new BidList();
        newBidList.setBidListId(5555);
        newBidList.setBidQuantity(1.0);
        newBidList.setAccount("accountTest2");
        newBidList.setType("typeTest2");
        when(bidListRepository.save(newBidList)).thenReturn(newBidList);
        bidListService.create(newBidList);
        when(bidListRepository.findById(5555)).thenReturn(Optional.of(newBidList));
        assertEquals(1.0,bidListService.getById(5555).getBidQuantity());
        bidListService.delete(5555);
    }

    @Test
    public void updateTest(){
        bidList.setType("typeUpdateTest");
        when(bidListRepository.save(bidList)).thenReturn(bidList);
        bidListService.update(bidList);
        when(bidListRepository.findById(2555)).thenReturn(Optional.ofNullable(bidList));
        assertEquals("typeUpdateTest",bidListService.getById(2555).getType());
    }

    //TODO
    /*@Test
    public void deleteTest(){
        BidList newBidList = new BidList();
        newBidList.setBidListId(5555);
        newBidList.setBidQuantity(1.0);
        newBidList.setAccount("accountTest2");
        newBidList.setType("typeTest2");
        when(bidListRepository.save(newBidList)).thenReturn(newBidList);
        bidListService.create(newBidList);
        when(bidListRepository.findById(5555)).thenReturn(Optional.of(newBidList));
        assertEquals(1.0,bidListService.getById(5555).getBidQuantity());
        bidListService.delete(5555);
        when(bidListRepository.findById(5555)).thenReturn(null);
        assertEquals(null,bidListService.getById(5555));
    }*/

    @Test
    public void getByIdTest(){
        when(bidListRepository.findById(2555)).thenReturn(Optional.of(bidList));
        assertEquals("accountTest",bidListService.getById(2555).getAccount());
    }

    @Test
    public void updateBidListTest(){
        bidList.setType("typeUpdateTest");
        when(bidListRepository.save(bidList)).thenReturn(bidList);
        bidListService.updateBidList(2555,bidList);
        when(bidListRepository.findById(2555)).thenReturn(Optional.ofNullable(bidList));
        assertEquals("typeUpdateTest",bidListService.getById(2555).getType());
    }

}
