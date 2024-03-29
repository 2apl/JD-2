package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "employee_sequence", schema = "employee_storage")
public class EmployeeSequence {

    @Id
    @GeneratedValue(generator = "employee_gen", strategy = GenerationType.SEQUENCE)
    /**
     * for mapping sequence from DB (when id not bigserial type)
     * we can define any behavior for generating id (--, +2, ...)
     * */
    @SequenceGenerator(name = "employee_gen", schema = "employee_storage",
            sequenceName = "employee_id_sequence", allocationSize = 1)
    /**
     * also might be GenericGenerator for your custom generating ids
     * */
    private Long id;

    @Column(name = "name")
    private String name;

    public EmployeeSequence(String name) {
        this.name = name;
    }

}
