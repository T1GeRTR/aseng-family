package com.aseng.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 5, message = "Не меньше 5 знаков")
    private String username;
    @Size(min = 5, message = "Не меньше 5 знаков")
    private String password;
    @Size(min = 2, message = "Не меньше 2 знаков")
    private String firstName;
    @Size(min = 2, message = "Не меньше 2 знаков")
    private String lastName;
    @Size(min = 5, message = "Не меньше 5 знаков")
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Hours> hours;
    private boolean created; //Пользователь может быть импортирован из ПланФикс или создан отделом кадров (Бизнес требования)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(Long id, String username, String password, String firstName, String lastName, String email, Role role) {
        this(id, username, password, firstName, lastName, email, new ArrayList<>(), false, role);
    }

    public User(String username, String password, String firstName, String lastName, String email, boolean created, Role role) {
        this(0L, username, password, firstName, lastName, email, new ArrayList<>(), created, role);
    }

    public User(String username, String password, String firstName, String lastName, String email, Role role) {
        this(username, password, firstName, lastName, email, true, role);
    }

    public User(String firstName, String lastName) {
        this(null, null, firstName, lastName, null, Role.EMPLOYEE);
    }
}
