package com.example.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
/**
 * Approach if we need entity from intermediate ManyToMany table via appropriate class
 * */
@Entity
@Table(name = "employee_meeting", schema = "employee_storage")
public class EmployeeMeeting extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Column(name = "meeting_date")
    private LocalDateTime meetingDate;
}
