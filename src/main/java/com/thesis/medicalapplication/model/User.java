package com.thesis.medicalapplication.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @NotEmpty
    @NotNull(message = "Nick jest obowiązkowy")
    @Column(name = "username")
    private String username;

    @NotEmpty
    @NotNull(message = "Hasło jest obowiązkowe")
    @Length(min = 5, message = "Hasło musi mieć przynajmniej 5 znaków")
    @Column(name = "password")
    private String password;

    @NotEmpty
    @NotNull(message = "Email jest obowiązkowy")
    @Email(message = "Email jest nieprawidłowy")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<UserRole> userRoles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<Record> records;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    Patient patient;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    Doctor doctor;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<Consultation> consultations;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<MedicalVisit> medicalVisits;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<Medicine> medicines;

    @OneToMany(mappedBy = "reportedUser", fetch = FetchType.EAGER)
    Set<Bug> bugs;

}
