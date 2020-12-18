package com.alya.system.user.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alya
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @GetMapping(value = "/test")
    public void test(){
        System.out.println("success");
    }


}
