package com.nhnacademy.controller;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SetLocaleController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String locale = req.getParameter("locale");
        ServletContext servletContext = req.getServletContext();

        servletContext.setAttribute("locale",locale);

        return "redirect:/";
    }
}
