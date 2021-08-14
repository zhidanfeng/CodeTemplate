package com.zhi.controller;

import com.zhi.core.entity.User;
import com.zhi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> listAll() {
        return this.userService.listAll();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        this.userService.addUser(user);
    }
}