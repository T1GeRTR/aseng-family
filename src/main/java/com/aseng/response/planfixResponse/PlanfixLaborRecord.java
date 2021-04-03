package com.aseng.response.planfixResponse;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class PlanfixLaborRecord {
    @SerializedName("FirstName")
    private String firstname;
    @SerializedName("LastName")
    private String lastname;
    @SerializedName("StartTime")
    private String startString;
    private LocalDateTime startTime;
    @SerializedName("FinishTime")
    private String finishString;
    private LocalDateTime finishTime;
    @SerializedName("LaborSpan")
    private String laborString;
    private LocalTime laborSpan;
    @SerializedName("TaskId")
    private long taskId;
    @SerializedName("TaskTitle")
    private String taskTitle;
    @SerializedName("ProjectId")
    private long projectId;
    @SerializedName("ProjectTitle")
    private String projectTitle;

    public PlanfixLaborRecord(String firstname, String lastname, String startTime, String finishTime, String laborSpan, long taskId, String taskTitle, long projectId, String projectTitle) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.startString = startTime;
        this.finishString = finishTime;
        this.laborString = laborSpan;
        setStartTime();
        setFinishTime();
        setLaborSpan();
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
    }

    public PlanfixLaborRecord(String firstname, String lastname, LocalDateTime startTime, LocalDateTime finishTime, LocalTime laborSpan, long taskId, String taskTitle, long projectId, String projectTitle) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.startString = startTime.toString();
        this.finishString = finishTime.toString();
        this.laborSpan = laborSpan;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
    }

    public void setLocalDateTime(){
        setStartTime();
        setFinishTime();
        setLaborSpan();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }


    private void setStartTime() {
        String[] array = startString.split("T");
        String[] arrayDate = array[0].split("-");
        String[] arrayTime = array[1].split(":");
        LocalDate date = LocalDate.of(Integer.parseInt(arrayDate[0]), Integer.parseInt(arrayDate[1]), Integer.parseInt(arrayDate[2]));
        LocalTime time = LocalTime.of(Integer.parseInt(arrayTime[0]), Integer.parseInt(arrayTime[1]), Integer.parseInt(arrayTime[2]));
        this.startTime = LocalDateTime.of(date, time);
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    private void setFinishTime() {
        String[] array = finishString.split("T");
        String[] arrayDate = array[0].split("-");
        String[] arrayTime = array[1].split(":");
        LocalDate date = LocalDate.of(Integer.parseInt(arrayDate[0]), Integer.parseInt(arrayDate[1]), Integer.parseInt(arrayDate[2]));
        LocalTime time = LocalTime.of(Integer.parseInt(arrayTime[0]), Integer.parseInt(arrayTime[1]), Integer.parseInt(arrayTime[2]));
        this.finishTime = LocalDateTime.of(date, time);
    }

    public LocalTime getLaborSpan() {
        return laborSpan;
    }

    private void setLaborSpan() {
        String[] arrayTime = laborString.split(":");
        this.laborSpan = LocalTime.of(Integer.parseInt(arrayTime[0]), Integer.parseInt(arrayTime[1]), Integer.parseInt(arrayTime[2]));
    }

    public String getLaborString() {
        return laborString;
    }

    public void setLaborString(String laborString) {
        this.laborString = laborString;
    }

    public void setLaborSpan(LocalTime laborSpan) {
        this.laborSpan = laborSpan;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getStartString() {
        return startString;
    }

    public void setStartString(String startString) {
        this.startString = startString;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getFinishString() {
        return finishString;
    }

    public void setFinishString(String finishString) {
        this.finishString = finishString;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanfixLaborRecord)) return false;
        PlanfixLaborRecord that = (PlanfixLaborRecord) o;
        return getTaskId() == that.getTaskId() && getProjectId() == that.getProjectId() && Objects.equals(getFirstname(), that.getFirstname()) && Objects.equals(getLastname(), that.getLastname()) && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getFinishTime(), that.getFinishTime()) && Objects.equals(getLaborSpan(), that.getLaborSpan()) && Objects.equals(getTaskTitle(), that.getTaskTitle()) && Objects.equals(getProjectTitle(), that.getProjectTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname(), getStartTime(), getFinishTime(), getLaborSpan(), getTaskId(), getTaskTitle(), getProjectId(), getProjectTitle());
    }
}
