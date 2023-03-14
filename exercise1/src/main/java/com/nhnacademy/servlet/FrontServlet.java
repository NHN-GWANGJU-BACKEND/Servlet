package com.nhnacademy.servlet;

import com.nhnacademy.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        Command command = resolveUrl(req.getServletPath());
        String view = command.execute(req,resp);

        if(view.startsWith(REDIRECT_PREFIX)){
            resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
        }else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
            requestDispatcher.include(req,resp);
        }
    }

    public Command resolveUrl(String servletPath){
        Command command = null;
        if(servletPath.equals("/foods.do")){
            command = new FoodController();
        }else if(servletPath.equals("/init.do")){
            command = new InitController();
        }else if(servletPath.equals("/cartForm.do")){
            command = new CartFormController();
        }else if(servletPath.equals("/cart.do")){
            command = new CartController();
        }else if(servletPath.equals("/login.do")){
            command = new LoginController();
        }else if(servletPath.equals("/loginForm.do")){
            command = new LoginFormController();
        }else if(servletPath.equals("/logout.do")){
            command = new LogoutController();
        }else if(servletPath.equals("/set-locale.do")){
            command = new SetLocaleController();
        }else if(servletPath.equals("/pay.do")){
            command = new PayController();
        }

        return command;
    }
}
