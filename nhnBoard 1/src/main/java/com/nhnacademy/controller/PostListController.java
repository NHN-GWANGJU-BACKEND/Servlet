package com.nhnacademy.controller;

import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PostListController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        PostRepository mapPostRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");

        List<Post> postList = mapPostRepository.getPosts();

        req.setAttribute("postList",postList);

        return "/postList.jsp";
    }
}
