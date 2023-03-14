package com.nhnacademy.servlet;

import com.nhnacademy.controller.*;
import com.nhnacademy.controller.post.*;
import com.nhnacademy.controller.user.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(
        location = "/Users/taewon/Documents/tmp",
        maxFileSize = -1L,
        maxRequestSize = -1L,
        fileSizeThreshold = 1024
)
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

        if(servletPath.equals("/login.do")){
            command = new LoginController();
        }else if(servletPath.equals("/user/register.do")){
            command = new UserRegisterController();
        }else if(servletPath.equals("/userList.do")){
            command = new UserListController();
        }else if(servletPath.equals("/user/modify.do")){
            command = new UserModifyController();
        }else if(servletPath.equals("/user/view.do")){
            command = new UserController();
        }else if(servletPath.equals("/user/delete.do")){
            command = new UserDeleteController();
        }else if(servletPath.equals("/logout.do")){
            command = new LogoutController();
        }else if(servletPath.equals("/postList.do")){
            command = new PostListController();
        }else if(servletPath.equals("/post/modify.do")){
            command = new PostModifyController();
        }else if(servletPath.equals("/post/register.do")){
            command = new PostRegisterController();
        }else if(servletPath.equals("/post/delete.do")){
            command = new PostDeleteController();
        }else if(servletPath.equals("/post/view.do")){
            command = new PostController();
        }else if(servletPath.equals("/set-locale.do")){
            command = new SetLocaleController();
        }

        return command;
    }
}
