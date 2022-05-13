package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {
    Logger logger = LoggerFactory.getLogger(BidListService.class);
    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> read(){
        return  bidListRepository.findAll();
    }

    public void create(BidList bidList){
        bidListRepository.save(bidList);
        logger.info("BidList : " + bidList + " is created");
    }

    public void update(BidList bidList){
        bidListRepository.save(bidList);
    }

    public void delete(int bidListId){
        bidListRepository.deleteById(bidListId);
        logger.info("BidList with id " + bidListId + " is deleted");

        /*boolean deleted = false;
        Optional<BidList> list = bidListRepository.findById(bidListId);
        if (list.isPresent()) {
            deleted = true;
            bidListRepository.deleteById(bidListId);
            logger.info("BidList with id " + bidListId + " is deleted");
        }else{
            logger.error("Failed to delete BidList with id " + bidListId +);
        }*/

    }

    public BidList getById(Integer id) {
        return bidListRepository.findById(id).get();
    }

    public Boolean updateBidList(Integer id, BidList bidList) {
        boolean updated = false;
        Optional<BidList> list = bidListRepository.findById(id);
        if (list.isPresent()) {
            BidList newBidList = list.get();
            newBidList.setAccount(bidList.getAccount());
            newBidList.setType(bidList.getType());
            newBidList.setBidQuantity(bidList.getBidQuantity());
            bidListRepository.save(newBidList);
            updated = true;
            logger.info("BidList with id " + id + " is updated as " + newBidList);
        } else {
            logger.error("Failed to update BidList with id " + id + " as " + bidList);
        }
        return updated;
    }
}
