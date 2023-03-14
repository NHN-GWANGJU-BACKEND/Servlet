package com.nhnacademy.controller;

import javax.servlet.http.*;
import java.util.Objects;
public class LogoutController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if(Objects.nonNull(session.getAttribute("id"))){
            session.invalidate();
        }
        return "redirect:/";
    }
}
