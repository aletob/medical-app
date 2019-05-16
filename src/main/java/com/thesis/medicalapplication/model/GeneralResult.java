package com.thesis.medicalapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "general_result")
public class GeneralResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_id")
    private int resultId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Podaj czego dotyczy wynik")
    @Column(name = "parameter")
    private String parameter;

    @NotNull(message = "Podaj wynik badania")
    @Column(name = "description", length = 512)
    private String description;

    @NotNull(message = "Podaj datę badania")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "time")
    private Date time;
}
