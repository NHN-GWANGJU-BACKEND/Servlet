package com.nhnacademy.servlet;

import com.nhnacademy.controller.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy");

        Command command = null;

        if(servletPath.equals("/login.do")){
            command = (Command) context.getBean("loginController");
        }else if(servletPath.equals("/user/register.do")){
            command = (Command) context.getBean("userRegisterController");
        }else if(servletPath.equals("/userList.do")){
            command = (Command) context.getBean("userListController");
        }else if(servletPath.equals("/user/modify.do")){
            command = (Command) context.getBean("userModifyController");
        }else if(servletPath.equals("/user/view.do")){
            command = (Command) context.getBean("userController");
        }else if(servletPath.equals("/user/delete.do")){
            command = (Command) context.getBean("userDeleteController");
        }else if(servletPath.equals("/logout.do")){
            command = (Command) context.getBean("logoutController");
        }else if(servletPath.equals("/postList.do")){
            command = (Command) context.getBean("postListController");
        }else if(servletPath.equals("/post/modify.do")){
            command = (Command) context.getBean("postModifyController");
        }else if(servletPath.equals("/post/register.do")){
            command = (Command) context.getBean("postRegisterController");
        }else if(servletPath.equals("/post/delete.do")){
            command = (Command) context.getBean("postDeleteController");
        }else if(servletPath.equals("/post/view.do")){
            command = (Command) context.getBean("postController");
        }else if(servletPath.equals("/set-locale.do")){
            command = (Command) context.getBean("setLocaleController");
        }

        return command;
    }
}
