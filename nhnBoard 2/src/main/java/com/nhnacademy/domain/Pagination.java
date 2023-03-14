package com.nhnacademy.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Pagination implements Page{
    private int pageNumber;
    private int pageSize;
    private List<Post> list;

    private long totalCount;

    public Pagination() {
    }

    public Pagination(int page, int size,List<Post> list, int totalCount) {
        this.pageNumber = page;
        this.pageSize = size;
        this.list = list;
        this.totalCount = totalCount;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPageCount() {
        boolean plusOne = true;
        if(totalCount%pageSize == 0){
            plusOne = false;
        }
        int totalPageCount = (int) (totalCount/pageSize);
        if(plusOne){
            totalPageCount+=1;
        }
        return totalPageCount;
    }

    @Override
    public List<Post> getList() {
        int start = ((getPageNumber()-1)*getPageSize())+1;
        int end = start+getPageSize();

        return list.stream().filter(post -> post.getId()>=start && post.getId()<end)
                .collect(Collectors.toList());
    }

    @Override
    public long getTotalCount() {
        return totalCount;
    }
}
