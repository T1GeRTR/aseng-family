package com.aseng.response;

import com.aseng.entity.Hours;
import com.aseng.entity.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class UserDtoResponse implements UserDetails {
    private Long id;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String username;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;
    @Size(min=2, message = "Не меньше 2 знаков")
    private String firstName;
    @Size(min=2, message = "Не меньше 2 знаков")
    private String lastName;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String email;
    private boolean created; //Пользователь может быть импортирован из ПланФикс или создан отделом кадров (Бизнес требования)
    private Role role;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    @Override
    public String getPassword() {
        return password;
    }
}
