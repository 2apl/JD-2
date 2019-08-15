package com.example.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "programmer", schema = "employee_storage")
/**
 * if SINGLE_TABLE InheritanceType
 * */
//@DiscriminatorValue("PROGRAMMER")
public class Programmer extends Employee {

    @Column(name = "language")
    private Language language;

    public Programmer(String name, Gender gender, Language language) {
        super();}

}
