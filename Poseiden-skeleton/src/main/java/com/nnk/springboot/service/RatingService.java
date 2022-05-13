package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> read(){
        return  ratingRepository.findAll();
    }

    public void create(Rating rating){
        ratingRepository.save(rating);
        logger.info("Rating : " + rating + " is created");
    }

    public void update(Rating rating){
        ratingRepository.save(rating);
    }

    public void delete(int ratingId){
        ratingRepository.deleteById(ratingId);
        logger.info("Rating with id " + ratingId + " is deleted");
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
            logger.info("Rating with id " + id + " is updated as " + newRating);
        } else {
            logger.error("Failed to update Rating with id " + id + " as " + rating);
        }
        return updated;
    }

}
