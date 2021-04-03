package com.aseng.utils;

import com.aseng.exception.ErrorCode;
import com.aseng.exception.ServerException;

import java.time.LocalDate;

public class MonthYearConverter {

    public static int getMonth(String monthYear) throws ServerException {
        int month;
        String[] split = splitString(monthYear);
        month = Integer.parseInt(split[0]);
        if (month > 12) {
            throw new ServerException(ErrorCode.INVALID_MONTH);
        }
        return month;
    }

    public static int getYear(String monthYear) throws ServerException {
        int year;
        String[] split = splitString(monthYear);
        if (split.length == 2) {
            year = Integer.parseInt(split[1]);
        } else {
            year = LocalDate.now().getYear();
        }
        if (year > LocalDate.now().getYear()) {
            throw new ServerException(ErrorCode.INVALID_YEAR);
        }
        return year;
    }

    private static String[] splitString(String monthYear) throws ServerException {
        String[] array = monthYear.split("-");
        if (array.length > 2) {
            throw new ServerException(ErrorCode.INVALID_MONTH_YEAR);
        }
        return array;
    }
}
