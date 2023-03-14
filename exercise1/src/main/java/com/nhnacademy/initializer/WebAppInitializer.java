package com.nhnacademy.initializer;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionListener;
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
        ctx.setInitParameter("id","taewon");
        ctx.setInitParameter("password","1234");

    }
}
