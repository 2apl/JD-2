package com.example.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Entity
/**
 * TABLE_PER_CLASS singe table for each child class
 * SINGLE_TABLE all fields will be in one result table (bad normalization), but can use IDENTITY id generation type
 * JOINED separate tables (Employee, Manager, Programmer) for appropriate fields
 * */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/**
 * if SINGLE_TABLE InheritanceType
 * */
//@DiscriminatorColumn(name = "type")
public abstract class Employee extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
