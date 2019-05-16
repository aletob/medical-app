package com.thesis.medicalapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consultation_id")
    private int consultationId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull
    @Column(name = "question", length = 512)
    private String question;

    @Column(name = "answer", length = 512)
    private String answer;

    @Column(name = "question_date")
    private Date questionDate;

    @Column(name = "answer_date")
    private Date answerDate;
}
