package com.nhnacademy.listener;

import com.nhnacademy.domain.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        MapUserRepository mapUserRepository = new MapUserRepository();
        JsonUserRepository jsonUserRepository = new JsonUserRepository(servletContext);
        PostRepository mapPostRepository = new MapPostRepository();
        List<User> userList = new ArrayList<>();
        User adminUser = new UserDTO("admin", "12345", "관리자", "X");

        mapUserRepository.add(adminUser);

        String count = readLines(servletContext, "/WEB-INF/classes/visitCounter.txt");

        int visitCount = Integer.parseInt(count);

        servletContext.setAttribute("visitCount", visitCount);
        servletContext.setAttribute("mapUserRepository", mapUserRepository);
        servletContext.setAttribute("jsonUserRepository",jsonUserRepository);
        servletContext.setAttribute("mapPostRepository", mapPostRepository);
        servletContext.setAttribute("admin", adminUser);
        servletContext.setAttribute("locale", "ko");
        servletContext.setAttribute("userList", userList);
    }



    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
    }

    private String readLines(ServletContext servletContext, String fileName) {
        return new BufferedReader(
                new InputStreamReader(
                        servletContext.getResourceAsStream(fileName)
                )
        ).lines().collect(Collectors.joining());
    }
}