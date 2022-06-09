package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BidListServiceTest {

    @Autowired
    private BidListService bidListService;

    private BidList bidList;

    @Before
    public void setUp(){
        bidList = new BidList();
        bidList.setAccount("accountTest");
        bidList.setBidListId(2555);
        bidList.setBidQuantity(2.555);
        bidList.setType("typeTest");
    }
    @After
    public void end(){
        bidListService.delete(2555);
    }
    @BeforeEach
    public void create(){
        createTest();
    }
    @AfterEach
    public void delete(){
        deleteTest();
    }

    @Test
    public void readTest() {
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
        bidListService.create(newBidList);
        assertEquals(1.0,bidListService.getById(5555).getBidQuantity());
        bidListService.delete(5555);
    }

    @Test
    public void updateTest(){
        bidList.setType("typeUpdateTest");
        bidListService.update(bidList);
        assertEquals("typeUpdateTest",bidListService.getById(2555).getType());
    }

    @Test
    public void deleteTest(){
        BidList newBidList = new BidList();
        newBidList.setBidListId(5555);
        newBidList.setBidQuantity(1.0);
        newBidList.setAccount("accountTest2");
        newBidList.setType("typeTest2");
        bidListService.create(newBidList);
        assertEquals(1.0,bidListService.getById(5555).getBidQuantity());
        bidListService.delete(5555);
        assertEquals(null,bidListService.getById(5555));
    }

    @Test
    public void getByIdTest(){
        assertEquals("accountTest",bidListService.getById(2555).getAccount());
    }

    @Test
    public void updateBidListTest(){
        bidList.setType("typeUpdateTest");
        bidListService.updateBidList(2555,bidList);
        assertEquals("typeUpdateTest",bidListService.getById(2555).getType());
    }

}
