package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is appropriate to entity in DB
 */
@Entity
/**
 * Appropriate table in DB, name and schema are not required, but good practice, name is taking from class name
 * */
@Table(name = "employee", schema = "employee_storage")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    /**
     * AUTO - Hibernate defines one of the following 3 strategies (default IDENTITY)
     * IDENTITY - Id provided by DB table
     * SEQUENCE - sequence by DB table
     * TABLE - another separate table from DB provided ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * appropriate column in DB, not required, but good practice, name taking from field name
     */
    @Column(name = "name")
    private String name;

    public Employee(String name) {
        this.name = name;
    }

}
