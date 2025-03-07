package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class TradeController {
    // TODO: Inject Trade service
    @Autowired
    private TradeService tradeService;
    private Trade trade;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        List<Trade> trades = tradeService.read();
        model.addAttribute("trades",trades);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate( Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        if (!result.hasErrors()) {
            tradeService.create(trade);
            model.addAttribute("trades", tradeService.read());
            return "trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        trade = tradeService.getById(id);
        model.addAttribute("trade",trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id,  Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            return "trade/update";
        }
        Boolean updated = tradeService.updateTrade(id, trade);
        if (updated) {
            model.addAttribute("trades", tradeService.read());
        }
        return "trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        trade = tradeService.getById(id);
        if (trade != null) {
            tradeService.delete(id);
            model.addAttribute("trades", tradeService.read());
            return "trade/list";
        } else {
            throw new IllegalArgumentException("Invalid trade id: " + id);
        }
    }
}
