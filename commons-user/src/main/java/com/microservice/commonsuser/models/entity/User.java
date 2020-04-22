package com.microservice.commonsuser.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 30)
    private String username;
    @Column(length = 60)
    private String password;
    private Boolean enabled;
    private String name;
    private String surname;
    @Column(unique = true, length = 100)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = " user_to_role",joinColumns = @JoinColumn(name = "id_user")
            ,inverseJoinColumns = @JoinColumn(name = "id_role"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user","id_role"})})
    private List<Role> roles;

    private static final long serialVersionUID = 1L;
}
