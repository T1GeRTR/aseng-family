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
public class UsersHoursDtoRequest {
    private List<UserHoursDtoRequest> userList;
}
