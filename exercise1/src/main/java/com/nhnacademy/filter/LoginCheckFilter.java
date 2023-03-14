package com.nhnacademy.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "exclude_urls", value = "/\n/login.do\n/login.html")
})
@Slf4j
public class LoginCheckFilter implements Filter {
    private Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude_urls");
        excludeUrls = Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession(false);


        if (Objects.isNull(session)) {
            if (excludeUrls.contains(((HttpServletRequest) req).getRequestURI())) {
                filterChain.doFilter(req, resp);
            } else {
                ((HttpServletResponse) resp).sendRedirect("/login.html");
            }
        } else {
            if(session.getAttribute("id")!=null) {
                filterChain.doFilter(req, resp);
            }else{
                if (excludeUrls.contains(((HttpServletRequest) req).getRequestURI())) {
                    filterChain.doFilter(req, resp);
                } else {
                    ((HttpServletResponse) resp).sendRedirect("/login.html");
                }
            }
        }
    }
}
