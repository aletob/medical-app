package com.thesis.medicalapplication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;

    private String role;

    @OneToMany(mappedBy = "role")
    Set<User_role> user_roles;

/*    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
    Set<User> users;*/

}
