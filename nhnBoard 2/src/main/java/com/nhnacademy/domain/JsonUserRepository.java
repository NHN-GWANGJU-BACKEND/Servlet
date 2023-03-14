package com.nhnacademy.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JsonUserRepository implements UserRepository {
    private Map<String, User> map = new LinkedHashMap<>();

    public JsonUserRepository(ServletContext servletContext) {
        ObjectMapper mapper = new ObjectMapper();
        List<UserDTO> list = new ArrayList<>();
        String userJson = readLines(servletContext, "/WEB-INF/classes/users.json");
        try {
            list = mapper.readValue(userJson, new TypeReference<List<UserDTO>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("", e);
        }

        for (UserDTO user : list) {
            this.add(user);
        }

    }

    @Override
    public void add(User user) {
        map.put(user.getId(), user);
    }

    @Override
    public void modify(User user) {
        User origin = map.get(user.getId());

        if (!origin.getPassword().equals(user.getPassword())) {
            origin.setPassword(user.getPassword());

        }
        if (!origin.getName().equals(user.getName())) {
            origin.setName(user.getName());

        }
        if (!origin.getProfileFileName().equals(user.getProfileFileName())) {
            origin.setProfileFileName(user.getProfileFileName());
        }

    }

    @Override
    public User remove(String id) {
        return map.remove(id);
    }

    @Override
    public User getUser(String id) {
        return map.getOrDefault(id, null);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(map.values()).stream()
                .filter(user -> !user.getId().equals("admin"))
                .collect(Collectors.toList());
    }

    private String readLines(ServletContext servletContext, String fileName) {
        return new BufferedReader(
                new InputStreamReader(
                        servletContext.getResourceAsStream(fileName)
                )
        ).lines().collect(Collectors.joining());
    }
}
