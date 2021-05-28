package com.zti.yerbastore.controller.admin;

import com.zti.yerbastore.model.User;
import com.zti.yerbastore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        List<User> users = userService.findAll();
        users.forEach(user -> user.setPassword(null));

        return users;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody User user) {
        User savedUser = userService.insert(user);
        savedUser.setPassword(null);

        return savedUser;
    }

    @DeleteMapping(path = "/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

}
