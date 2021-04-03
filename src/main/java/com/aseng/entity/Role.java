package com.aseng.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public enum Role implements GrantedAuthority {
    EMPLOYEE(1, "ROLE_EMPLOYEE"), ADMIN(2, "ROLE_ADMIN"), GIP(3, "ROLE_GIP"), BOOKER(4, "ROLE_BOOKER");

    @Id
    private Long id;
    private String name;
    @Transient
    @OneToMany(mappedBy = "roles")
    private Set<User> users;

    Role(long id, String name) {
        this(id, name, new TreeSet<>());
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
