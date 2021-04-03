package com.aseng.exception;

public enum ErrorCode {
    CANT_GET_USER_BY_SESSION("sessionId", "Can't get user by sessionId"),
    CANT_LOGOUT("sessionId", "Can't logout"),
    CANT_LOGIN("login", "User with login %s not found"),
    CANT_GET_PATIENT_BY_ID("id", "Patient with id %d not found"),
    CANT_UPDATE_USER("login","Can't update user"),
    CANT_SAVE_USER("login","Can't save user"),
    CANT_INSERT_APPOINTMENT("doctorId", "Patient can't make appointment doctor more 1 per day"),
    APPOINTMENT_BUSY("doctorId", "Appointment in doctor's schedule busy"),
    CANT_GET_USER_BY_ID("userId", "User with userId %d not found"),
    DUPLICATE_LOGIN("login", "Login %s already exists"),
    DOCTOR_NOT_FOUND("id or speciality", "Doctor for this parameters not found"),
    WRONG_DOCTORS_COUNT("doctorIds", "Can't insert comission"),
    CANT_DELETE_COMISSION("id", "Comission with id %d not found"),
    CANT_DELETE_APPOINTMENT("id", "Can't find appointment with id %d for delete"),
    APPOINTMENT_NOT_FOUND("doctorId or speciality", "Search parameters incorrect"),
    WRONG_SEARCH_PARAMETERS("doctorId and speciality", "doctorId or speciality must be null or empty"),
    CANT_GET_ACCOUNT_INFO("sessionId", "Can't get user's profile info. Wrong sessionId"),
    CANT_GET_DOCTORS_BY_SPECIALITY("parameters", "Can't find doctors with this parameters"),
    CANT_GET_DOCTOR_BY_ID("id", "Can't get doctor by id %d"),
    WRONG_SCHEDULE("schedule", "WeekSchedule or weekDaysSchedule must be null or empty"),
    DOCTORS_NOT_FOUND("id", "Doctor with id %d not found"),
    DOCTORS_CANT_GET_WITH_PARAMS("speciality", "%s"),
    WRONG_SESSION_ID("sessionId", "Can't get user by sessionId"),
    WRONG_APPOINTMENT_DATE("date", "Date can't be more than 2 months later"),
    WRONG_DATE_INTERVAL("startDate and endDate", "StartDate can't be after endDate"),
    WRONG_YES_OR_NO_STRING("id", "Schedule must be 'yes' or 'no'"),
    PERMISSION_DENIED("userType", "Only %s have permission"),
    INTERNAL_SERVER_ERROR("internal error", "Internal server error 500"),
    WRONG_PHONE("phone", "Phone number incorrect"),
    WRONG_ROOM("room", "Room must be in doctors rooms"),
    MISSING_REQUEST_PARAM("request parameters", "%s"),
    WRONG_ARGUMENT_TYPE("argument type", "%s"),
    MISSING_COOKIE("cookie", "Missing request cookie, try again"),
    REST_CLIENT_EXCEPTION("cookie", "%s"),
    CANT_CLEAR_DATABASE("clear", "Can't clear database"),
    WRONG_PASSWORD("password", "Wrong password"),
    WRONG_END_DATE("endDate", "EndDate can't be after today or before startDate"),
    WRONG_START_DATE("startDate", "StartDate can't be after today"),
    CANT_DELETE_USER("id", "Can't delete user with id %d"),
    DATABASE_ERROR("query", "Database error"),
    ROOM_NOT_FOUND("room", "Room %s not found"),
    ROOM_IS_BUSY("room", "Room %s is busy"),
    SPECIALITY_NOT_FOUND("specilaity", "Specilaliyu %s not found"),
    DAY_IN_DOCTOR_SCHEDULE_NOT_FOUND("date", "Day in doctor's schedule not found"),
    PATIENT_BUSY("date time", "Patient is busy at this date and time"),
    CANT_UPDATE_HOURS("id","Can't update hours"),
    CANT_DELETE_HOURS("id","Can't delete hours"),
    HTTP_MESSAGE_NOT_READBLE("parameters", "Incorrect parameters"),
    PLANFIX_CANT_GET_USERS("getUsers", "Can't get users rom PlanFix"),
    PLANFIX_CANT_GET_HOURS("getUsers", "Can't get hours rom PlanFix"),
    INVALID_MONTH_YEAR("yearMonth", "Invalid month-year"),
    INVALID_YEAR("yearMonth", "Invalid year, max current year"),
    INVALID_MONTH("yearMonth", "Invalid month, max 12");


    private final String field;
    private String message;

    ErrorCode(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public void formatMessage(String string1) {
        message = String.format(message, string1);
    }

    public void formatMessage(int int1) {
        message = String.format(message, int1);
    }

}
