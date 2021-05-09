package com.zti.yerbastore.controller.admin;

import com.zti.yerbastore.model.User;
import com.zti.yerbastore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        List<User> users = userService.findAll();
        users.forEach(user -> user.setPassword(null));

        return users;
    }

    @DeleteMapping(value = "/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

}
