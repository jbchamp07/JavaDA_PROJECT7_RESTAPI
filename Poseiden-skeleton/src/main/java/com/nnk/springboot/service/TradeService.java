package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
