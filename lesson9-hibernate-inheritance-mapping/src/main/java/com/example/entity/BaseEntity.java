package com.example.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity<PK extends Serializable> {

    @Id
    /**
     * SEQUENCE generation type strategy, because for Programmer and Manager (both Employees) may be common ids in Identity strategy
     * */
    @GeneratedValue(generator = "employee_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq_id",
                schema = "employee_storage", allocationSize = 1)
    private PK id;
}
