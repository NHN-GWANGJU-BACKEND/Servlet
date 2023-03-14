package com.nhnacademy.controller.user;

import com.nhnacademy.controller.Command;
import com.nhnacademy.domain.UserRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserDeleteController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        ServletContext servletContext = req.getServletContext();
        UserRepository mapUserRepository = (UserRepository) servletContext.getAttribute("mapUserRepository");

        mapUserRepository.remove(userId);

        return "redirect:/";
    }
}
