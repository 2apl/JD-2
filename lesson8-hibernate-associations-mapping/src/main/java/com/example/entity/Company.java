package com.example.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
/**
 * method toString is launch from every mapping class (One to many and Many to One)
 * it may produce stack overflow, to avoid this situation we are excluding this field from method
 * */
@ToString(exclude = {"employees"})
@Entity
@Table(name = "company", schema = "employee_storage")
public class Company extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    /**
     * additional reverse annotation to ManyToOne, EAGER fetch type overrides LAZY by default
     * option orphanRemoval = true - when we delete element from collection, element will be deleted from DB too
     * option cascade - what we will do with employees in company, when we delete company
     * */
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private Set<Employee> employees = new HashSet<>();

    public Company(String name) {
        this.name = name;
    }

}
