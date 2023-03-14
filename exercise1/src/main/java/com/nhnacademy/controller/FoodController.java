package com.nhnacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FoodController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        return "redirect:/foodsView.jsp";
    }

}
