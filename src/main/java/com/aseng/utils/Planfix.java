package com.aseng.utils;

import com.aseng.exception.ErrorCode;
import com.aseng.exception.ServerException;
import com.aseng.response.planfixResponse.PlanfixLaborRecord;
import com.aseng.response.planfixResponse.PlanfixUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Planfix {
    private static final Logger LOGGER = LoggerFactory.getLogger(Planfix.class);

    public List<PlanfixUser> getUsers() throws ServerException {
        String cmd = "getUsers";
        try {
            Runtime rt = Runtime.getRuntime();
            LOGGER.debug("Planfix: " + cmd);
            Process p = rt.exec("./planfix/PlanFix.Console.exe " + cmd);
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line);
            }
            p.destroy();
            System.out.println(str);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<PlanfixUser>>() {
            }.getType();
            return gson.fromJson(str.toString(), listType);
        } catch (Exception exc) {
            throw new ServerException(ErrorCode.PLANFIX_CANT_GET_USERS);
        }
    }

    public String getProjects(String status) throws ServerException {
        String cmd = "getProjects ";
        try {
            Runtime rt = Runtime.getRuntime();
            LOGGER.debug("Planfix: " + cmd + status);
            Process p = rt.exec("./planfix/PlanFix.Console.exe " + cmd + status);
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line);
            }
            p.destroy();
            System.out.println(str);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<PlanfixUser>>() {
            }.getType();
            return str.toString();
        } catch (Exception exc) {
            throw new ServerException(ErrorCode.PLANFIX_CANT_GET_USERS);
        }
    }

    public List<PlanfixLaborRecord> getHours(LocalDate from, LocalDate to) throws ServerException {
        String fromMonth = (from.getMonthValue() < 10) ? "0" + from.getMonthValue() : String.valueOf(from.getMonthValue());
        String toMonth = (to.getMonthValue() < 10) ? "0" + to.getMonthValue() : String.valueOf(to.getMonthValue());
        String fromString = from.getYear() + "-" + fromMonth + "-" + from.getDayOfMonth();
        String toString = to.getYear() + "-" + toMonth + "-" + to.getDayOfMonth();
        String cmd = "getLaborRecords ";
        try {
            Runtime rt = Runtime.getRuntime();
            LOGGER.debug("Planfix: " + cmd + fromString + " " + toString);
            Process p = rt.exec("./planfix/PlanFix.Console.exe " + cmd + fromString + " " + toString);
            InputStream in = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line);
            }
            p.destroy();
            System.out.println(str);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<PlanfixLaborRecord>>() {
            }.getType();
            return gson.fromJson(str.toString(), listType);
        } catch (Exception exc) {
            throw new ServerException(ErrorCode.PLANFIX_CANT_GET_HOURS);
        }
    }
}
