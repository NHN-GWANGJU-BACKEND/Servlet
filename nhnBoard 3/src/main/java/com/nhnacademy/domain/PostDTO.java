package com.nhnacademy.domain;

import java.time.LocalDateTime;

public class PostDTO implements Post {
    private long id;
    private String title, content, writerUserId;
    private LocalDateTime writeTime;
    private int viewCount=0;

    public PostDTO() {
    }

    public PostDTO(String title, String content, String writerUserId, LocalDateTime writeTime) {
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = writeTime;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    public String getWriterUserId() {
        return writerUserId;
    }

    public void setWriterUserId(String writerUserId) {
        this.writerUserId = writerUserId;
    }

    @Override
    public LocalDateTime getWriteTime() {
        return writeTime;
    }

    @Override
    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }

    @Override
    public int getViewCount() {
        return viewCount;
    }

    @Override
    public void increaseViewCount() {
        this.viewCount++;
    }
}
