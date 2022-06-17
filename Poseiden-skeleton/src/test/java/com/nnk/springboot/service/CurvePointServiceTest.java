package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurvePointServiceTest {

    @InjectMocks
    private CurvePointService curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    private CurvePoint curvePoint;

    @Before
    public void setUp(){
        curvePoint = new CurvePoint();
        curvePoint.setId(2555);
        curvePoint.setCurveId(5555);
        curvePoint.setTerm(2.5);
        curvePoint.setValue(2.555);
        MockitoAnnotations.openMocks(this);
    }
    @After
    public void end(){
        //curvePointService.delete(2555);
    }
    @BeforeEach
    public void create(){
        curvePointService.create(curvePoint);
    }
    @AfterEach
    public void delete(){
        curvePointService.delete(2555);
    }

    @Test
    public void readTest() {
        List<CurvePoint> listCurvePoint = new ArrayList<>();
        listCurvePoint.add(curvePoint);
        when(curvePointRepository.findAll()).thenReturn(listCurvePoint);
        List<CurvePoint> list = curvePointService.read();
        assertEquals(2.5,list.get(list.size() - 1).getTerm());
    }

    @Test
    public void createTest(){
        CurvePoint newCurvePoint = new CurvePoint();
        newCurvePoint.setId(5555);
        newCurvePoint.setCurveId(2555);
        newCurvePoint.setTerm(3.5);
        newCurvePoint.setValue(25.55);
        when(curvePointRepository.save(newCurvePoint)).thenReturn(newCurvePoint);
        curvePointService.create(newCurvePoint);
        when(curvePointRepository.findById(5555)).thenReturn(Optional.of(newCurvePoint));
        assertEquals(25.55,curvePointService.getById(5555).getValue());
        curvePointService.delete(5555);
    }

    @Test
    public void updateTest(){
        curvePoint.setValue(2525.0);
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
        curvePointService.update(curvePoint);
        when(curvePointRepository.findById(2555)).thenReturn(Optional.ofNullable(curvePoint));
        assertEquals(2525.0,curvePointService.getById(2555).getValue());
    }

    //TODO
    /*@Test
    public void deleteTest(){
        CurvePoint newCurvePoint = new CurvePoint();
        newCurvePoint.setId(5555);
        newCurvePoint.setCurveId(2555);
        newCurvePoint.setTerm(3.5);
        newCurvePoint.setValue(25.55);
        when(curvePointRepository.save(newCurvePoint)).thenReturn(newCurvePoint);
        curvePointService.create(curvePoint);
        when(curvePointRepository.findById(5555)).thenReturn(Optional.of(newCurvePoint));
        assertEquals(3.5,curvePointService.getById(5555).getTerm());
        curvePointService.delete(5555);
        when(curvePointRepository.findById(5555)).thenReturn(null);
        assertEquals(null,curvePointService.getById(5555));
    }*/

    @Test
    public void getByIdTest(){
        when(curvePointRepository.findById(2555)).thenReturn(Optional.of(curvePoint));
        assertEquals(2.5,curvePointService.getById(2555).getTerm());
    }

    @Test
    public void updateBidListTest(){
        curvePoint.setValue(2525.0);
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
        curvePointService.updateCurvePoint(2555,curvePoint);
        when(curvePointRepository.findById(2555)).thenReturn(Optional.ofNullable(curvePoint));
        assertEquals(2525.0,curvePointService.getById(2555).getValue());

    }

}
