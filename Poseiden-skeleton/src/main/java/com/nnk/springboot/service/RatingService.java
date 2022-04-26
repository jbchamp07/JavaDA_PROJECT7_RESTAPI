package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
