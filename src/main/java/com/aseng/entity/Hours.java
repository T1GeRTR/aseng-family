package com.aseng.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_hours")
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Hours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private LocalDate date;
    private String hours; //Админ может вставить вместо 0 какое-либо буквенное обозначение (Бизнес требования)
    private boolean saved; //Админ может изменить часы (Бизнес требования)
    private int type;

    public Hours(Long id, User user, LocalDate date, String hours, int type) {
        this(id, user, date, hours, false, type);
    }

    public Hours() {
        setType(1);
    }

    public Hours(String hours, LocalDate date, User user, int type) {
        setHours(hours);
        setDate(date);
        setUser(user);
        setType(type);
    }

    public Hours(Long id, String hours) {
        this(id, null, null, hours, 1);
    }
}
