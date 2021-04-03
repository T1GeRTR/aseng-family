package com.aseng.request;

import lombok.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class UserHoursDtoRequest {
    private Long id;
    private String userName;
    private String firstname;
    private String email;
    private List<HoursDtoRequest> hours;
}
