package com.zti.yerbastore.controller;

import com.zti.yerbastore.model.User;
import com.zti.yerbastore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(value = "/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

}
