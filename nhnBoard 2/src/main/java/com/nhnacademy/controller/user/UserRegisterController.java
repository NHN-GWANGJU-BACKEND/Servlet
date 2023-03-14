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

@Slf4j
public class UserRegisterController implements Command {
    private final String UPLOAD_DIR = System.getProperty("user.dir")+"/image";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        UserRepository mapUserRepository = (UserRepository) servletContext.getAttribute("mapUserRepository");
        User user = new UserDTO();
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
                    }else{
                        continue;
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
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setProfileFileName(fileName);

        mapUserRepository.add(user);

        return "redirect:/";
    }

    public static String extractFileName(String header) {
        for (String token : header.split(";")) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 1).trim().replace("\"", "");
                int index = fileName.lastIndexOf(File.separator);
                return fileName.substring(index + 1);
            }
        }

        return null;
    }
}
