package com.nhnacademy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    public String execute(HttpServletRequest req, HttpServletResponse resp);
}
