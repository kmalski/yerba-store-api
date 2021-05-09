package com.zti.yerbastore.controller;

import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.service.YerbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/yerba", produces = MediaType.APPLICATION_JSON_VALUE)
public class YerbaController {

    private final YerbaService yerbaService;

    @Autowired
    public YerbaController(YerbaService yerbaService) {
        this.yerbaService = yerbaService;
    }

    @GetMapping
    public List<Yerba> getAll() {
        return yerbaService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Yerba getById(@PathVariable String id) {
        return yerbaService.findById(id);
    }

}
