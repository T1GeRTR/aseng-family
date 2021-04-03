package com.aseng.response;

import com.aseng.request.HoursDtoRequest;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class UserHoursDtoResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private List<HoursDtoResponse> hours;
}
