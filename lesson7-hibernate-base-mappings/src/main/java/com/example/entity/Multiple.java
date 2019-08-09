package com.example.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "multiple", schema = "employee_storage")
public class Multiple {

    /**
     * If Id is complex and embedded
     */
    @EmbeddedId
    @Column(unique = true)
    private ManyIds id;

    @Column(name = "name")
    private String name;

}
