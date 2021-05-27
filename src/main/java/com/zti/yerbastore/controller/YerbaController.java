package com.zti.yerbastore.controller;

import com.zti.yerbastore.aspect.WebsiteViewsTrigger;
import com.zti.yerbastore.aspect.YerbaViewsTrigger;
import com.zti.yerbastore.model.Photo;
import com.zti.yerbastore.model.Yerba;
import com.zti.yerbastore.service.PhotoService;
import com.zti.yerbastore.service.YerbaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/yerba", produces = MediaType.APPLICATION_JSON_VALUE)
public class YerbaController {

    private final YerbaService yerbaService;
    private final PhotoService photoService;

    @WebsiteViewsTrigger
    @GetMapping
    public List<Yerba> getAll() {
        return yerbaService.findAll();
    }

    @YerbaViewsTrigger
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
