package com.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "manager", schema = "employee_storage")
/**
 * if SINGLE_TABLE InheritanceType
 * */
//@DiscriminatorValue("MANAGER")
/**
 * if JOINED InheritanceType
 * */
//@PrimaryKeyJoinColumn(name = "employee_id")
public class Manager extends Employee {

    private String projectName;

    public Manager(String name, Gender gender, String projectName) {

    }

}
