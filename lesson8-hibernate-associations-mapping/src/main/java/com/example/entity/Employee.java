package com.example.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee", schema = "employee_storage")
public class Employee extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * many employees to one company
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    /**
     * for associations mapping instead of @Column
     * */
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * mappedBy = "employee" - field from Contact class where mapping was added
     * */
    @OneToOne(mappedBy = "employee")
    private Contact contact;

    @ManyToMany
    @JoinTable(name = "employee_meeting", schema = "employee_storage",
    /**
     * joinColumns - field in join table appropriate current entity from this class
     * */
    joinColumns = {@JoinColumn(name = "employee_id")},
    /**
     * inverseJoinColumns - field in join table appropriate mapping entity
     * */
    inverseJoinColumns = {@JoinColumn(name = "meeting_id")})
    private Set<Meeting> meetings = new HashSet<>();

    public Employee(String name, Gender gender, Company company) {
        this.name = name;
        this.gender = gender;
        this.company = company;
    }

    public Employee(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

}
