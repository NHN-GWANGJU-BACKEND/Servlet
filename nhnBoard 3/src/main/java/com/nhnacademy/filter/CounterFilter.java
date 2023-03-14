package com.nhnacademy.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
@Slf4j
@WebFilter(filterName = "counterFilter", urlPatterns = {"/post/view.do","/user/view.do"})
public class CounterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        ServletContext servletContext = req.getServletContext();
        int visitCount = (int) servletContext.getAttribute("visitCount");

        servletContext.setAttribute("visitCount", ++visitCount);

        try {
            File file = new File(servletContext.getRealPath("/WEB-INF/classes/visitCounter.txt"));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(Integer.toString(visitCount));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(req, resp);
    }
}
