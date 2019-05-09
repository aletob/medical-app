package com.thesis.medicalapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    private int doctorId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "second_name")
    private String secondName;

    @Column(name = "adress")
    private String address;

    @Column(name = "hospital")
    private String hospital;

    @NotNull
    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    Set<Consultation> consultations;
}
