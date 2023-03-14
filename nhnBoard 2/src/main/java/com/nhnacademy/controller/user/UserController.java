package com.nhnacademy.controller.user;

import com.nhnacademy.controller.Command;
import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        PostRepository postRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");
        String userId = req.getParameter("userId");
        List<Post> postList =
        postRepository.getPosts().stream()
                .filter(post -> post.getWriterUserId().equals(userId))
                .collect(Collectors.toList());

        servletContext.setAttribute("postList",postList);
        return "/user.jsp";
    }
}
