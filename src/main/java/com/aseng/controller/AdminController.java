package com.aseng.controller;

import com.aseng.entity.DayOfWeek;
import com.aseng.exception.ServerException;
import com.aseng.request.UsersHoursDtoRequest;
import com.aseng.response.UsersHoursDtoResponse;
import com.aseng.service.UserService;
import com.aseng.utils.MonthYearConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return "admin";
    }

    @RequestMapping(value = {"/admin/{monthYear}"}, method = RequestMethod.GET)
    String getFromDate(@PathVariable("monthYear") String monthYear, Model model) throws ServerException {
        int month = MonthYearConverter.getMonth(monthYear);
        int year = MonthYearConverter.getYear(monthYear);
        List<Integer> daysOfMonth = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();
        for (int i = 0; i < LocalDate.of(year, month, 1).lengthOfMonth(); i++) {
            daysOfMonth.add(i + 1);
            daysOfWeek.add(DayOfWeek.getDay(LocalDate.of(year, month, i+1).getDayOfWeek()).getDay());
        }
        model.addAttribute("users", new UsersHoursDtoResponse(userService.getFromDate(monthYear), LocalDate.of(year, month, 1).lengthOfMonth()));
        model.addAttribute("days", daysOfMonth);
        model.addAttribute("weeks", daysOfWeek);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "usersFromDate";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.POST)
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

//    @GetMapping("/admin/gt/{userId}")
//    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.usergtList(userId));
//        return "admin";
//    }
}
