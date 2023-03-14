package com.nhnacademy.controller;

import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class PostController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        PostRepository postRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");
        String postId = req.getParameter("postId");

        Post post = postRepository.getPost(Long.parseLong(postId));

        req.setAttribute("post",post);

        return "/postView.jsp";
    }
}
