package com.nhnacademy.controller;

import com.nhnacademy.domain.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostDeleteController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String postId = req.getParameter("postId");
        ServletContext servletContext = req.getServletContext();
        PostRepository mapPostRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");

        mapPostRepository.remove(Long.parseLong(postId));

        return "redirect:/postList.do";
    }
}
