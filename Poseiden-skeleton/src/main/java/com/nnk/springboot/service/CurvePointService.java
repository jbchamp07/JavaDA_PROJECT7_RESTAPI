package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public CurvePoint getById(Integer id) {
        return curvePointRepository.findById(id).get();
    }

    public Boolean updateCurvePoint(Integer id, CurvePoint curvePoint) {
        boolean updated = false;
        Optional<CurvePoint> list = curvePointRepository.findById(id);
        if (list.isPresent()) {
            CurvePoint newCurvePoint = list.get();
            newCurvePoint.setCurveId(curvePoint.getCurveId());
            newCurvePoint.setTerm(curvePoint.getTerm());
            newCurvePoint.setValue(curvePoint.getValue());
            curvePointRepository.save(newCurvePoint);
            updated = true;
            //logger.info("BidList with id " + id + " is updated as " + newBidList);
        } else {
            //logger.error("Failed to update BidList with id " + id + " as " + bidList);
        }
        return updated;
    }
}
