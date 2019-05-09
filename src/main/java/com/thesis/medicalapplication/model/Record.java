package com.thesis.medicalapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private int recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Podaj czego dotyczy wynik")
    @Column(name = "parameter")
    private String parameter;

    @NotNull(message = "Podaj wartość parametru")
    @Column(name = "value")
    private double value;

    @Column(name = "unit")
    private String unit;

    @Column(name = "norm")
    private double norm;

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
