package com.nhnacademy.controller;

import com.nhnacademy.domain.MapPost;
import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;


@Slf4j
public class PostModifyController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        PostRepository mapPostRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");

        String postId = req.getParameter("postId");
        Post post = mapPostRepository.getPost(Long.parseLong(postId));

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        try {
            if (Objects.nonNull(post)) {
                post.setTitle(title);
                post.setContent(content);
                post.setWriteTime(LocalDateTime.now());
                mapPostRepository.modify(post);
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            log.error("",e);
        }

        return "redirect:/post/list.do";
    }
}
