package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
@Data
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    @Column(name="name")
    String name;
    @Column(name="description")
    String description;
    @Column(name="json")
    String json;
    @Column(name="template")
    String template;
    @Column(name="sqlStr")
    String sqlStr;
    @Column(name="sqlPart")
    String sqlPart;
}
