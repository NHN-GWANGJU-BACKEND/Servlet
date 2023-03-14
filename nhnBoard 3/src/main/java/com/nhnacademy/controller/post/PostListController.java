package com.nhnacademy.controller.post;

import com.nhnacademy.controller.Command;
import com.nhnacademy.domain.Post;
import com.nhnacademy.domain.PostRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class PostListController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));

        ServletContext servletContext = req.getServletContext();
        PostRepository mapPostRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");

        List<Post> postList = mapPostRepository.getPagedPosts(page,size).getList();

        req.setAttribute("postList",postList);
        req.setAttribute("page",page);
        req.setAttribute("size",size);

        return "/postList.jsp";
    }
}
