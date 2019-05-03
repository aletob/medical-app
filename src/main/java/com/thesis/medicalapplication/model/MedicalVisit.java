package com.thesis.medicalapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medical_visit")
public class MedicalVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visit_id")
    private int visitId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @NotEmpty
    @Column(name = "date")
    private Date date;

    @Column(name = "place")
    private String place;

    @Column(name = "doctor")
    private String doctor;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "description")
    private String description;
}
