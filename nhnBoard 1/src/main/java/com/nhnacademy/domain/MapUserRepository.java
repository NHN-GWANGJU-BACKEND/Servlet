package com.nhnacademy.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class MapUserRepository implements UserRepository {
    private Map<String, User> map = new HashMap<>();

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
}
