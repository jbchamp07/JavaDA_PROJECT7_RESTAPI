package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {
    // TODO: Inject Rating service
    @Autowired
    private RatingService ratingService;
    private Rating rating;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        List<Rating> ratings = ratingService.read();
        model.addAttribute("ratings",ratings);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate( Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        if (!result.hasErrors()) {
            ratingService.create(rating);
            model.addAttribute("ratings", ratingService.read());
            return "rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        rating = ratingService.getById(id);
        model.addAttribute("rating",rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id,  Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            return "rating/update";
        }
        Boolean updated = ratingService.updateRating(id, rating);
        if (updated) {
            model.addAttribute("ratings", ratingService.read());
        }
        return "rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        rating = ratingService.getById(id);
        if (rating != null) {
            ratingService.delete(id);
            model.addAttribute("ratings", ratingService.read());
            return "rating/list";
        } else {
            throw new IllegalArgumentException("Invalid rating id: " + id);
        }
    }
}
