package com.aseng.response;

import com.aseng.request.UserHoursDtoRequest;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class UsersHoursDtoResponse {
    private List<UserHoursDtoResponse> userList;
    private int monthLen;
}
