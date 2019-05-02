package com.thesis.medicalapplication.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @Column(name = "parameter")
    private String parameter;

    @Column(name = "value")
    private double value;

    @Column(name = "unit")
    private String unit;

    @Column(name = "norm")
    private double norm;

   @DateTimeFormat(pattern = "yyyy-MM-dd")
   @Column(name = "date")
    private Date date;

   @Column(name = "time")
    private String time;
}
