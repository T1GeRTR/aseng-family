package com.aseng.response.planfixResponse;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PlanfixUser {
    @SerializedName("Id")
    private int id;
    @SerializedName("FirstName")
    private String firstname;
    @SerializedName("LastName")
    private String lastname;
    @SerializedName("Email")
    private String email;

    public PlanfixUser(int id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanfixUser)) return false;
        PlanfixUser user = (PlanfixUser) o;
        return getId() == user.getId() && Objects.equals(getFirstname(), user.getFirstname()) && Objects.equals(getLastname(), user.getLastname()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail());
    }
}
