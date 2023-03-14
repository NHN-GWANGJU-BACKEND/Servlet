package com.nhnacademy.controller;

import com.nhnacademy.domain.User;
import com.nhnacademy.domain.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        UserRepository mapUserRepository = (UserRepository) servletContext.getAttribute("mapUserRepository");

        List<User> users = mapUserRepository.getUsers();

        req.setAttribute("userList",users);

        return "/userList.jsp";
    }
}
