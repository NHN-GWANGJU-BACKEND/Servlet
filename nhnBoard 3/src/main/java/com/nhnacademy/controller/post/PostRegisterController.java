package com.nhnacademy.controller.post;

import com.nhnacademy.controller.Command;
import com.nhnacademy.domain.PostDTO;
import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.PostRepository;
import com.nhnacademy.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Component
public class PostRegisterController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        ServletContext servletContext = req.getServletContext();
        PostRepository mapPostRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Post post = new PostDTO();

        int postId = (int) servletContext.getAttribute("postId");

        post.setId(++postId);
        post.setContent(content);
        post.setTitle(title);
        post.setWriteTime(LocalDateTime.now());
        post.setWriterUserId(user.getId());

        mapPostRepository.register(post);
        servletContext.setAttribute("postId", postId);

        return "redirect:/";

    }
}
