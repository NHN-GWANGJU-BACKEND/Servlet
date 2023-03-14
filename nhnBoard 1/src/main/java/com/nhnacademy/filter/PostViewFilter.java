package com.nhnacademy.filter;

import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.PostRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "postViewFilter", urlPatterns = "/post/view.do")
public class PostViewFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        long postId = Long.parseLong(req.getParameter("postId"));
        ServletContext servletContext = req.getServletContext();
        PostRepository mapPostRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");

        Post post = mapPostRepository.getPost(postId);
        post.increaseViewCount();

        filterChain.doFilter(req,resp);
    }
}
