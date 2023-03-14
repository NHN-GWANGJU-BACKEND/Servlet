package com.nhnacademy.controller;

import com.nhnacademy.domain.User;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.util.List;
import java.util.Objects;
public class LogoutController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        ServletContext servletContext = req.getServletContext();

        if(Objects.nonNull(session.getAttribute("user"))){
            User user = (User) session.getAttribute("user");
            List<User> userList = (List<User>) servletContext.getAttribute("userList");
            userList.remove(user);
            session.invalidate();
        }
        return "redirect:/";
    }
}
