import com.nhnacademy.controller.*;
import com.nhnacademy.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostTest {
    HttpServletRequest req;
    HttpServletResponse resp;
    UserRepository mapUserRepository;
    PostRepository mapPostRepository;
    ServletContext servletContext;
    HttpSession session;
    User admin = new MapUser("admin", "12345", "admin", "");
    Post post = new MapPost("title","content","taewon", LocalDateTime.now());

    @BeforeEach
    public void init() {
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        mapUserRepository = mock(UserRepository.class);
        mapPostRepository = mock(PostRepository.class);
        servletContext = mock(ServletContext.class);
        session = mock(HttpSession.class);
        when(req.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("mapPostRepository")).thenReturn(mapPostRepository);
        when(req.getSession()).thenReturn(session);
        when(req.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(admin);
        when(mapPostRepository.getPosts()).thenReturn(new ArrayList<>());
        when(req.getParameter("postId")).thenReturn("0");

    }

    @Test
    public void postList() {
        Command command = new PostListController();
        String result = command.execute(req, resp);
        Assertions.assertThat(result).isEqualTo("/postList.jsp");
    }
    @Test
    public void postView() {
        Command command = new PostController();
        String result = command.execute(req, resp);
        Assertions.assertThat(result).isEqualTo("/postView.jsp");
    }

    @Test
    public void postDelete(){
        Command command = new PostDeleteController();
        String result = command.execute(req, resp);
        Assertions.assertThat(result).isEqualTo("redirect:/postList.do");
    }

    @Test
    public void postModify(){
        when(mapPostRepository.getPost(Long.parseLong("0"))).thenReturn(post);
        when(req.getParameter("title")).thenReturn("title");
        when(req.getParameter("content")).thenReturn("content");

        Command command = new PostModifyController();
        String result = command.execute(req, resp);
        Assertions.assertThat(result).isEqualTo("redirect:/post/list.do");
    }

    @Test
    public void postRegister(){
        when(session.getAttribute("user")).thenReturn(admin);
        when(servletContext.getAttribute("postId")).thenReturn(0);

        Command command = new PostRegisterController();
        String result = command.execute(req, resp);
        Assertions.assertThat(result).isEqualTo("redirect:/");
    }


}
