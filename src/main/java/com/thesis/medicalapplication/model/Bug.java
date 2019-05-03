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
@Table(name = "bug")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bug_id")
    private int bugId;

    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    private User reportedUser;

    @NotEmpty
    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "report_date")
    private Date reportDate;

    @Column(name = "fixed")
    private boolean fixed;
}
