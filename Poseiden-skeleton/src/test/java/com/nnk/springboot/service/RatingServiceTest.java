package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RatingTest;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RatingServiceTest {

    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepository;

    private Rating rating;

    @Before
    public void setUp(){
        rating = new Rating();
        rating.setId(2555);
        rating.setSandPRating("sandTest");
        rating.setMoodysRating("moodyTest");
        rating.setOrderNumber(10);
        rating.setFitchRating("fitchTest");
        MockitoAnnotations.openMocks(this);
    }
    @After
    public void end(){
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
        List<Rating> listRating = new ArrayList<>();
        listRating.add(rating);
        when(ratingRepository.findAll()).thenReturn(listRating);
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
        when(ratingRepository.save(newRating)).thenReturn(newRating);
        ratingService.create(newRating);
        when(ratingRepository.findById(5555)).thenReturn(Optional.of(newRating));
        assertEquals(102,ratingService.getById(5555).getOrderNumber());
        ratingService.delete(5555);
    }

    @Test
    public void updateTest(){
        rating.setOrderNumber(102);
        when(ratingRepository.save(rating)).thenReturn(rating);
        ratingService.update(rating);
        when(ratingRepository.findById(2555)).thenReturn(Optional.ofNullable(rating));
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

        final Rating entity = newRating;
        Optional<Rating> optionalEntityType = Optional.of(entity);
        Mockito.when(ratingRepository.findById(5555)).thenReturn(optionalEntityType);
        ratingService.delete(5555);
        Mockito.verify(ratingRepository, times(1)).deleteById(entity.getId());
    }

    @Test
    public void getByIdTest(){
        when(ratingRepository.findById(2555)).thenReturn(Optional.of(rating));
        assertEquals("moodyTest",ratingService.getById(2555).getMoodysRating());
    }

    @Test
    public void updateBidListTest(){
        rating.setOrderNumber(102);
        when(ratingRepository.save(rating)).thenReturn(rating);
        ratingService.updateRating(2555,rating);
        when(ratingRepository.findById(2555)).thenReturn(Optional.ofNullable(rating));
        assertEquals(102,ratingService.getById(2555).getOrderNumber());

    }
    @Test
    public void updateBidListTestIf(){
        rating.setOrderNumber(102);
        when(ratingRepository.save(rating)).thenReturn(rating);
        when(ratingRepository.findById(2555)).thenReturn(Optional.ofNullable(rating));
        ratingService.updateRating(2555,rating);
        assertEquals(102,ratingService.getById(2555).getOrderNumber());

    }

}
