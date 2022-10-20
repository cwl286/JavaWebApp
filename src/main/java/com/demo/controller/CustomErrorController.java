package com.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

public class CustomErrorController implements ErrorController {

    /**
     * error router
     * @return error html
     */
    @GetMapping(value = "/error")
    public String error() {
        return "error";
    }
}
