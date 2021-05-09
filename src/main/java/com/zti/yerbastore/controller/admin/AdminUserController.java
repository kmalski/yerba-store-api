package com.zti.yerbastore.controller.admin;

import com.zti.yerbastore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(value = "/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

}
