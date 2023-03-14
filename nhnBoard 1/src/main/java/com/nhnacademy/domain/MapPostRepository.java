package com.nhnacademy.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapPostRepository implements PostRepository {
    private Map<Long, Post> map = new HashMap<>();

    @Override
    public long register(Post post) {
        map.put(post.getId(),post);
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
}
