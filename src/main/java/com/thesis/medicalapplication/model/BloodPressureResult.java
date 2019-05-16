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
@Table(name = "blood_pressure_result")
public class BloodPressureResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_id")
    private int resultId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Podaj wartość ciśnienia skurczowego")
    @Column(name = "systolic")
    private int systolic;

    @NotNull(message = "Podaj wartość ciśnienia rozkurczowego")
    @Column(name = "diastolic")
    private int diastolic;

    @Column(name = "pulse")
    private int pulse;

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
