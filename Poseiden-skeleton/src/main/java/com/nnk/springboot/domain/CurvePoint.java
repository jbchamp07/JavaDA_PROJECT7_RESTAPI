package com.nnk.springboot.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Data
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    @Column(name="curveId")
    Integer curveId;
    @Column(name="asOfDate")
    Timestamp asOfDate;
    @Column(name="term")
    Double term;
    @Column(name="value")
    Double value;
    @Column(name="creationDate")
    Timestamp creationDate;


}
