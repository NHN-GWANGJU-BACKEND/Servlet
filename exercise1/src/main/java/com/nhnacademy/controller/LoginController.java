package com.nhnacademy.controller;

import com.nhnacademy.domain.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        ServletContext servletContext = req.getServletContext();

        String correctId = servletContext.getInitParameter("id");
        String correctPassword = servletContext.getInitParameter("password");

        if(id.equals(correctId) && password.equals(correctPassword)){
            HttpSession session = req.getSession(true);
            session.setAttribute("id",id);
            User user = new User();
            user.setId(id);
            user.setMoney(20000);
            servletContext.setAttribute("user",user);
            return "redirect:/";
        }else{
            return "/login.html";
        }
    }


}
