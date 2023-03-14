package com.nhnacademy.domain;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapPostRepository implements PostRepository {
    private Map<Long, Post> map = new ConcurrentHashMap<>();

    @Override
    public long register(Post post) {
        map.put(post.getId(), post);
        return post.getId();
    }

    @Override
    public void modify(Post post) {
        // 따로 처리해 줄 필요가 없었습니다.
    }

    @Override
    public Post remove(long id) {
        return map.remove(id);
    }

    @Override
    public Post getPost(long id) {
        return map.getOrDefault(id, null);
    }

    @Override
    public List<Post> getPosts() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int getTotalCount() {
        return map.size();
    }

    @Override
    public Page<Post> getPagedPosts(int page, int size) {
        return new Pagination(page, size, getPosts(),getTotalCount());
    }
}
