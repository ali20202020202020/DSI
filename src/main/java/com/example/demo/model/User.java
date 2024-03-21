package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(nullable=false)
    String firstName;
    String lastName;
    String email;
    @Column(nullable=false, unique = true)
    String password;
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @JoinTable (name ="user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id ", referencedColumnName = "id"
            )})
    public List<Role> roles = new ArrayList<>();

    public User(Long id, String firstName,String LastName, String email, String password, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}

