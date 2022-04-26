package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> read(){
        return  bidListRepository.findAll();
    }

    public void create(BidList bidList){
        bidListRepository.save(bidList);
    }

    public void update(BidList bidList){
        bidListRepository.save(bidList);
    }

    public void delete(int bidListId){
        bidListRepository.deleteById(bidListId);
    }

}
