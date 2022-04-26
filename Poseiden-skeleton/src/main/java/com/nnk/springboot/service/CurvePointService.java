package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    public List<CurvePoint> read(){
        return  curvePointRepository.findAll();
    }

    public void create(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
    }

    public void update(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
    }

    public void delete(int curvePointId){
        curvePointRepository.deleteById(curvePointId);
    }

}
