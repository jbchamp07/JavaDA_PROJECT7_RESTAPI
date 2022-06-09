package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
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
public class CurvePointServiceTest {

    @Autowired
    private CurvePointService curvePointService;

    private CurvePoint curvePoint;

    @Before
    public void setUp(){
        curvePoint = new CurvePoint();
        curvePoint.setId(2555);
        curvePoint.setCurveId(5555);
        curvePoint.setTerm(2.5);
        curvePoint.setValue(2.555);
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
        curvePointService.create(newCurvePoint);
        assertEquals(25.55,curvePointService.getById(5555).getValue());
        curvePointService.delete(5555);
    }

    @Test
    public void updateTest(){
        curvePoint.setValue(2525.0);
        curvePointService.update(curvePoint);
        assertEquals(2525.0,curvePointService.getById(2555).getValue());
    }

    @Test
    public void deleteTest(){
        CurvePoint newCurvePoint = new CurvePoint();
        newCurvePoint.setId(5555);
        newCurvePoint.setCurveId(2555);
        newCurvePoint.setTerm(3.5);
        newCurvePoint.setValue(25.55);
        curvePointService.create(curvePoint);
        assertEquals(3.5,curvePointService.getById(5555).getTerm());
        curvePointService.delete(5555);
        assertEquals(null,curvePointService.getById(5555));
    }

    @Test
    public void getByIdTest(){
        assertEquals(2.5,curvePointService.getById(2555).getTerm());
    }

    @Test
    public void updateBidListTest(){
        curvePoint.setValue(2525.0);
        curvePointService.updateCurvePoint(2555,curvePoint);
        assertEquals(2525.0,curvePointService.getById(2555).getValue());

    }

}
