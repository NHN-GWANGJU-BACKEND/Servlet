package com.nhnacademy.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.util.stream.Collectors;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String count = readLines(servletContext, "/WEB-INF/classes/visitCounter.txt");
        int visitCount = Integer.parseInt(count);
        servletContext.setAttribute("visitCount", visitCount);
        servletContext.setAttribute("locale", "ko");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private String readLines(ServletContext servletContext, String fileName) {
        return new BufferedReader(
                new InputStreamReader(
                        servletContext.getResourceAsStream(fileName)
                )
        ).lines().collect(Collectors.joining());
    }
}