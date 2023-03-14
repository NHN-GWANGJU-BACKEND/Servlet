package com.nhnacademy.listener;

import com.nhnacademy.domain.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MapUserRepository mapUserRepository = new MapUserRepository();
        PostRepository mapPostRepository = new MapPostRepository();
        ServletContext servletContext = sce.getServletContext();

        User adminUser = new MapUser("admin", "12345", "관리자", "X");
        mapUserRepository.add(adminUser);

        List<User> userList = new ArrayList<>();

        servletContext.setAttribute("mapUserRepository", mapUserRepository);
        servletContext.setAttribute("mapPostRepository", mapPostRepository);
        servletContext.setAttribute("admin", adminUser);
        servletContext.setAttribute("locale", "ko");
        servletContext.setAttribute("userList",userList);

        String counterFile = servletContext.getInitParameter("visitCounterFile");
        String counterFilePath = "/WEB-INF/classes/" + counterFile;


        String count = "0";
        try {
            count = new String(servletContext.getResourceAsStream(counterFilePath).readAllBytes());
        } catch (IOException e) {
            log.error("", e);
        }
        int visitCount = Integer.parseInt(count);

        servletContext.setAttribute("visitCount", visitCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String counterFile = servletContext.getInitParameter("visitCounterFile");
        String counterFilePath = "/WEB-INF/classes/" + counterFile;

        int counter = (int) servletContext.getAttribute("counter");

        File file = new File(counterFilePath);

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(counter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}