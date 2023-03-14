package com.nhnacademy.initializer;

import com.nhnacademy.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@HandlesTypes({
        HttpServlet.class,
        Filter.class,
        ServletContextListener.class,
        HttpSessionListener.class
})
@Slf4j
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ctx.setAttribute("postId",0);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy");
        UserRepository mapUserRepository = (UserRepository) context.getBean("mapUserRepository");
        PostRepository mapPostRepository = (PostRepository) context.getBean("mapPostRepository");

        List<User> userList = new ArrayList<>();
        User adminUser = new UserDTO("admin", "12345", "관리자", "X");

        mapUserRepository.add(adminUser);

        ctx.setAttribute("admin", adminUser);
        ctx.setAttribute("userList", userList);
        ctx.setAttribute("mapUserRepository", mapUserRepository);
        ctx.setAttribute("mapPostRepository", mapPostRepository);
    }
}
