package com.nhnacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
       return "redirect:/cart.jsp";
    }
}
