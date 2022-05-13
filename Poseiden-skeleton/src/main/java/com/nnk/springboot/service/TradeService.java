package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    Logger logger = LoggerFactory.getLogger(TradeService.class);
    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> read(){
        return  tradeRepository.findAll();
    }

    public void create(Trade trade){
        tradeRepository.save(trade);
        logger.info("Trade : " + trade + " is created");
    }

    public void update(Trade trade){
        tradeRepository.save(trade);
    }

    public void delete(int tradeId){
        tradeRepository.deleteById(tradeId);
        logger.info("Trade with id " + tradeId + " is deleted");
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
            logger.info("Trade with id " + id + " is updated as " + newTrade);
        } else {
            logger.error("Failed to update Trade with id " + id + " as " + trade);
        }
        return updated;
    }

}
