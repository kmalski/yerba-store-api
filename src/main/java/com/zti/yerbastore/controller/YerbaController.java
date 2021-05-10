package com.zti.yerbastore.controller;

import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.service.PhotoService;
import com.zti.yerbastore.service.YerbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/yerba", produces = MediaType.APPLICATION_JSON_VALUE)
public class YerbaController {

    private final YerbaService yerbaService;
    private final PhotoService photoService;

    @Autowired
    public YerbaController(YerbaService yerbaService, PhotoService photoService) {
        this.yerbaService = yerbaService;
        this.photoService = photoService;
    }

    @GetMapping
    public List<Yerba> getAll() {
        return yerbaService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Yerba getById(@PathVariable String id) {
        return yerbaService.findById(id);
    }

    @GetMapping(path = "/{id}/photo", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getYerbaPhoto(@PathVariable String id) {
        Yerba yerba = yerbaService.findById(id);
        Photo photo = photoService.findById(yerba.getPhoto().getId());

        return photo.getImage().getData();
    }

}
