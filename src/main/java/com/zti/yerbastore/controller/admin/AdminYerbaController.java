package com.zti.yerbastore.controller.admin;

import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.service.YerbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/yerba", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminYerbaController {

    private final YerbaService yerbaService;

    @Autowired
    public AdminYerbaController(YerbaService yerbaService) {
        this.yerbaService = yerbaService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Yerba create(@RequestBody Yerba yerba) {
        return yerbaService.save(yerba);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        yerbaService.deleteById(id);
    }
}
