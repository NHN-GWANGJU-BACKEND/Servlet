package com.nhnacademy.controller;

import com.nhnacademy.domain.MapUserRepository;
import com.nhnacademy.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Component
public class LoginController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();

        MapUserRepository mapUserRepository = (MapUserRepository) servletContext.getAttribute("mapUserRepository");

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        User user = mapUserRepository.getUser(id);

        if(Objects.isNull(user)){
            return "redirect:/login.html";
        }else{
            if(password.equals(user.getPassword())){
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                List<User> userList = (List<User>) servletContext.getAttribute("userList");
                userList.add(user);
                return "redirect:/";
            }else{
                return "redirect:/login.html";
            }
        }
    }
}
