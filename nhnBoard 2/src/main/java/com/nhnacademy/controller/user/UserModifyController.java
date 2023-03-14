package com.nhnacademy.controller.user;

import com.nhnacademy.controller.Command;
import com.nhnacademy.domain.UserDTO;
import com.nhnacademy.domain.User;
import com.nhnacademy.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.util.Objects;

import static com.nhnacademy.controller.user.UserRegisterController.extractFileName;

@Slf4j
public class UserModifyController implements Command {
    private final String UPLOAD_DIR = System.getProperty("user.dir")+"/image";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        UserRepository mapUserRepository = (UserRepository) servletContext.getAttribute("mapUserRepository");

        User newUser = new UserDTO();
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String fileName = "/default.png";

        try {
            for (Part part : req.getParts()) {
                String header = part.getHeader("Content-Disposition");
                if (header.contains("filename=")) {
                    String tmp = extractFileName(header);
                    if (!tmp.equals("")) {
                        fileName = File.separator + tmp;
                    }
                    if (part.getSize() > 0) {
                        part.write(UPLOAD_DIR + File.separator + fileName);
                        part.delete();
                    } else {
                        String formValue = req.getParameter(part.getName());
                        log.error("{}={}", part.getName(), formValue);
                    }
                }
            }
        } catch (Exception e) {
        }

        newUser.setId(id);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setProfileFileName(fileName);

        User user = mapUserRepository.getUser(id);
        try {
            if (Objects.nonNull(user)) {
                mapUserRepository.modify(newUser);
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            log.error("",e);
        }

        return "redirect:/";
    }
}
