package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class RatingServiceTest {

    @Autowired
    private RatingService ratingService;

    private Rating rating;

    @Before
    public void setUp(){
        rating = new Rating();
        rating.setId(2555);
        rating.setSandPRating("sandTest");
        rating.setMoodysRating("moodyTest");
        rating.setOrderNumber(10);
        rating.setFitchRating("fitchTest");
    }
    @After
    public void end(){
        //ratingService.delete(2555);
    }
    @BeforeEach
    public void create(){
        ratingService.create(rating);
    }
    @AfterEach
    public void delete(){
        ratingService.delete(2555);
    }

    @Test
    public void readTest() {
        List<Rating> list = ratingService.read();
        assertEquals("fitchTest",list.get(list.size() - 1).getFitchRating());
    }

    @Test
    public void createTest(){
        Rating newRating = new Rating();
        newRating.setId(5555);
        newRating.setSandPRating("sandTest2");
        newRating.setMoodysRating("moodyTest2");
        newRating.setOrderNumber(102);
        newRating.setFitchRating("fitchTest2");
        ratingService.create(newRating);
        assertEquals(102,ratingService.getById(5555).getOrderNumber());
        ratingService.delete(5555);
    }

    @Test
    public void updateTest(){
        rating.setOrderNumber(102);
        ratingService.update(rating);
        assertEquals(102,ratingService.getById(2555).getOrderNumber());
    }

    @Test
    public void deleteTest(){
        Rating newRating = new Rating();
        newRating.setId(5555);
        newRating.setSandPRating("sandTest2");
        newRating.setMoodysRating("moodyTest2");
        newRating.setOrderNumber(102);
        newRating.setFitchRating("fitchTest2");
        ratingService.create(newRating);
        assertEquals(102,ratingService.getById(5555).getOrderNumber());
        ratingService.delete(5555);
        assertEquals(null,ratingService.getById(5555));
    }

    @Test
    public void getByIdTest(){
        assertEquals("moodyTest",ratingService.getById(2555).getMoodysRating());
    }

    @Test
    public void updateBidListTest(){
        rating.setOrderNumber(102);
        ratingService.updateRating(2555,rating);
        assertEquals(102,ratingService.getById(2555).getOrderNumber());

    }

}
