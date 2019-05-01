package com.thesis.medicalapplication.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @NotEmpty
    @NotNull(message = "Nick jest obowiązkowy")
    private String username;

    @NotEmpty
    @NotNull(message = "Hasło jest obowiązkowe")
    @Length(min = 5, message = "Hasło musi mieć przynajmniej 5 znaków")
    private String password;

    @NotEmpty
    @NotNull(message = "Email jest obowiązkowy")
    @Email(message = "Email jest nieprawidłowy")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    Set<User_role> user_roles;

/*    //@OneToMany(cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    // private Set<Role> roles = new HashSet<>();
            Set<Role> roles;*/

}
