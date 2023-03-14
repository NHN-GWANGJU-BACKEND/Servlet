import com.nhnacademy.controller.*;
import com.nhnacademy.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {
    HttpServletRequest req;
    HttpServletResponse resp;
    UserRepository mapUserRepository;
    PostRepository mapPostRepository;
    ServletContext servletContext;
    HttpSession session;
    User admin = new MapUser("admin", "12345", "admin", "");
    List<User> userList;
    Post post;


    @BeforeEach
    public void init() {
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        mapUserRepository = mock(MapUserRepository.class);
        mapPostRepository = mock(PostRepository.class);
        servletContext = mock(ServletContext.class);
        session = mock(HttpSession.class);
        when(req.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("mapUserRepository")).thenReturn(mapUserRepository);
        when(req.getSession(false)).thenReturn(session);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(admin);
    }

    @Test
    public void login(){
        when(req.getParameter("id")).thenReturn("admin");
        when(req.getParameter("password")).thenReturn("12345");
        when(servletContext.getAttribute("userList")).thenReturn(new ArrayList<>());
        when(mapUserRepository.getUser("admin")).thenReturn(admin);

        Command command = new LoginController();
        String result = command.execute(req,resp);

        Assertions.assertThat(result).isEqualTo("redirect:/");
    }

    @Test
    public void logout(){
        when(servletContext.getAttribute("userList")).thenReturn(new ArrayList<>());

        Command command = new LogoutController();
        String result = command.execute(req,resp);
        Assertions.assertThat(result).isEqualTo("redirect:/");
    }

    @Test
    public void userDelete(){
        Command command = new UserDeleteController();
        String result = command.execute(req,resp);
        Assertions.assertThat(result).isEqualTo("redirect:/");
    }

    @Test
    public void userList(){
        Command command = new UserListController();
        String result = command.execute(req,resp);
        Assertions.assertThat(result).isEqualTo("/userList.jsp");
    }

    @Test
    public void userModify(){
        when(req.getParameter("id")).thenReturn("admin");
        when(req.getParameter("password")).thenReturn("12345");
        when(req.getParameter("name")).thenReturn("admin");

        when(mapUserRepository.getUser("admin")).thenReturn(admin);


        Command command = new UserModifyController();
        String result = command.execute(req,resp);
        Assertions.assertThat(result).isEqualTo("redirect:/");
    }

    @Test
    public void userRegister(){
        Command command = new UserRegisterController();
        String result = command.execute(req,resp);
        Assertions.assertThat(result).isEqualTo("redirect:/");
    }

    @Test
    public void locale(){
        Command command = new SetLocaleController();
        String result = command.execute(req, resp);
        Assertions.assertThat(result).isEqualTo("redirect:/");
    }
}
