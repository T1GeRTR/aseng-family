package com.aseng.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_laborRecord")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class LaborRecord {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private LocalDate date;
    private float hours;
    private int taskId; //Хранится только в PlanFix
    private String taskTitle; //Хранится только в PlanFix
    private int projectId; //Хранится только в PlanFix
    private String projectTitle; //Хранится только в PlanFix

    public LaborRecord(User user, LocalDate date, float hours, int taskId, String taskTitle, int projectId, String projectTitle) {
        this(0L, user, date, hours, taskId, taskTitle, projectId, projectTitle);
    }
}
