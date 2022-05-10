package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> read(){
        return  ratingRepository.findAll();
    }

    public void create(Rating rating){
        ratingRepository.save(rating);
    }

    public void update(Rating rating){
        ratingRepository.save(rating);
    }

    public void delete(int RatingId){
        ratingRepository.deleteById(RatingId);
    }

    public Rating getById(Integer id) {
        return ratingRepository.findById(id).get();
    }

    public Boolean updateRating(Integer id, Rating rating) {
        boolean updated = false;
        Optional<Rating> list = ratingRepository.findById(id);
        if (list.isPresent()) {
            Rating newRating = list.get();
            newRating.setMoodysRating(rating.getMoodysRating());
            newRating.setFitchRating(rating.getFitchRating());
            newRating.setSandPRating(rating.getSandPRating());
            newRating.setOrderNumber(rating.getOrderNumber());
            ratingRepository.save(newRating);
            updated = true;
            //logger.info("BidList with id " + id + " is updated as " + newBidList);
        } else {
            //logger.error("Failed to update BidList with id " + id + " as " + bidList);
        }
        return updated;
    }

}
