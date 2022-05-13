package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@Data
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    @Column(name="moodysRating")
    String moodysRating;
    @Column(name="sandPRating")
    String sandPRating;
    @Column(name="fitchRating")
    String fitchRating;
    @Column(name="orderNumber")
    Integer orderNumber;
}
