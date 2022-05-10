package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> read(){
        return  tradeRepository.findAll();
    }

    public void create(Trade trade){
        tradeRepository.save(trade);
    }

    public void update(Trade trade){
        tradeRepository.save(trade);
    }

    public void delete(int TradeId){
        tradeRepository.deleteById(TradeId);
    }

    public Trade getById(Integer id) {
        return tradeRepository.findById(id).get();
    }

    public Boolean updateTrade(Integer id, Trade trade) {
        boolean updated = false;
        Optional<Trade> list = tradeRepository.findById(id);
        if (list.isPresent()) {
            Trade newTrade = list.get();
            newTrade.setAccount(trade.getAccount());
            newTrade.setType(trade.getType());
            newTrade.setBuyQuantity(trade.getBuyQuantity());
            tradeRepository.save(newTrade);
            updated = true;
            //logger.info("BidList with id " + id + " is updated as " + newBidList);
        } else {
            //logger.error("Failed to update BidList with id " + id + " as " + bidList);
        }
        return updated;
    }

}
