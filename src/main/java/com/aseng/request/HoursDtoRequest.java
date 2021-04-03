package com.aseng.request;

import com.aseng.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(value = "handler")
public class HoursDtoRequest {
    private Long id;
    private User user;
    private LocalDate date;
    private String hours;
    private boolean saved;
    private int type;
}
