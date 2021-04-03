package com.aseng.response;

import com.aseng.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(value = "handler")
public class LaborRecordDtoResponse {
    private Long id;
    private User user;
    private LocalDate date;
    private float hours;
    private String taskId;
    private String taskTitle;
    private String projectId;
    private String projectTitle;
}
