package com.example.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ManyIds implements Serializable {

    @Column(name = "first_id")
    private Long firstId;

    @Column(name = "second_id")
    private Long secondId;

}
